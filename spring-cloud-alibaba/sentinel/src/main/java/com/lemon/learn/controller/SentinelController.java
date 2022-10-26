package com.lemon.learn.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.lemon.learn.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sentinel")
@Slf4j
public class SentinelController {

    private static final String FLOW_RESOURCE_NAME = "flow";
    private static final String USER_FLOW_RESOURCE_NAME = "user";
    private static final String DEGRADE_RESOURCE_NAME = "degrade";

    // 配置流控规则
    @PostConstruct //spring初始化方法
    private static void initFlowRules(){
        // 流控规则
        List<FlowRule> rules = new ArrayList<>();

        // 流控
        FlowRule rule1 = new FlowRule();
        // 设置受保护的资源
        rule1.setResource(FLOW_RESOURCE_NAME);
        // 设置流控规则 QPS
        rule1.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 设置受保护的资源阈值
        // Set limit QPS to 20
        rule1.setCount(1);
        rules.add(rule1);

        // 通过@SentinelResource来定义资源并配置降级和流控的处理方法
        FlowRule rule2 = new FlowRule();

        // 设置受保护的资源
        rule2.setResource(USER_FLOW_RESOURCE_NAME);

        // 设置流控规则 QPS
        rule2.setGrade(RuleConstant.FLOW_GRADE_QPS);

        // 设置受保护的资源阈值
        // Set limit QPS to 20
        rule2.setCount(1);

        rules.add(rule2);

        // 加载配置好的规则
        FlowRuleManager.loadRules(rules);
    }

    // sentinel流控 一般在服务提供端
    // 代码实现
    @GetMapping("/flow")
    public String flow(String id){
        Entry entry = null;
        try {
            // sentinel 针对资源进行限制
            entry = SphU.entry(FLOW_RESOURCE_NAME);
            // 被保护的业务逻辑
            String str = "hello world";
            log.info("=====" + str + "=====");
            return str;
        }catch (BlockException blockException){
            // 资源访问阻止，被限流或被降级
            // 进行相应的处理
            log.info("block!");
            return "被流控了！";
        }catch (Exception e){
            // 若需要配置降级规则，需要通过这种方式记录业务异常
            Tracer.traceEntry(e, entry);
        }finally {
            if (entry != null){
                entry.exit();
            }
        }
        return null;
    }

    // sentinel流控
    // @SentinelResource注解实现
    @GetMapping("/user")
    @SentinelResource(value = USER_FLOW_RESOURCE_NAME, blockHandler = "blockHandlerForUser", fallback = "fallbackForUser")
    public Message user(String id){
        //int i = 1/0;
        return new Message("lemon");
    }

    // 流控方法
    public Message blockHandlerForUser(String id, BlockException blockException){
        blockException.printStackTrace();
        return new Message("流控 ！");
    }

    // 异常处理
    public Message fallbackForUser(String id, Throwable throwable){
        throwable.printStackTrace();
        return new Message("异常");
    }

    // 配置降级规则
    @PostConstruct  // 初始化
    public void initDegradeRule(){
        /*降级规则 异常*/
        List<DegradeRule> degradeRules = new ArrayList<>();
        DegradeRule degradeRule = new DegradeRule();
        degradeRule.setResource(DEGRADE_RESOURCE_NAME);

        // 设置规则侧率： 异常数
        degradeRule.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT);

        // 触发熔断异常数 ： 2
        degradeRule.setCount(2);

        // 触发熔断最小请求数:2
        degradeRule.setMinRequestAmount(2);

        // 统计时长：  单位：ms    1分钟
        degradeRule.setStatIntervalMs(60*1000); //  时间太短不好测

        // 一分钟内： 执行了2次  出现了2次异常  就会触发熔断
        // 熔断持续时长 ： 单位 秒
        // 一旦触发了熔断， 再次请求对应的接口就会直接调用  降级方法。
        // 10秒过了后——半开状态： 恢复接口请求调用， 如果第一次请求就异常， 再次熔断，不会根据设置的条件进行判定
        degradeRule.setTimeWindow(10);

        degradeRules.add(degradeRule);

        /*
        慢调用比率--DEGRADE_GRADE_RT
        degradeRule.setGrade(RuleConstant.DEGRADE_GRADE_RT);
        degradeRule.setCount(100);
        degradeRule.setTimeWindow(10);
        //请求总数小于minRequestAmount时不做熔断处理
        degradeRule.setMinRequestAmount(2);
        // 在这个时间段内2次请求
        degradeRule.setStatIntervalMs(60*1000*60);   //  时间太短不好测
        // 慢请求率：慢请求数/总请求数> SlowRatioThreshold ，
        // 这里要设置小于1   因为慢请求数/总请求数 永远不会大于1
        degradeRule.setSlowRatioThreshold(0.9);*/

        DegradeRuleManager.loadRules(degradeRules);
    }

    //sentinel熔断降级 一般在服务消费端
    @GetMapping("/degrade")
    @SentinelResource(value = DEGRADE_RESOURCE_NAME, entryType = EntryType.IN, blockHandler = "blockHandlerForDegrade")
    public Message degrade(String id) throws InterruptedException {
        // 异常数\比例
        throw new RuntimeException("异常处理");

        /*TimeUnit.SECONDS.sleep(1);
        return new Message("正常");*/
    }

    //熔断降级方法
    public Message blockHandlerForDegrade(String id, BlockException blockException){
        blockException.printStackTrace();
        return new Message("熔断降级");
    }

}

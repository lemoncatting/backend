package com.lemon.learn.exception;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lemon.learn.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class FowBlockHandler implements BlockExceptionHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws Exception {

        log.info("BlockExceptionHandler ===========" + e.getRule());
        Result result = null;

        if (e instanceof FlowException) {
            result = new Result<>(100, "接口限流了", null);
        } else if (e instanceof DegradeException) {
            result = new Result<>(101, "服务降级了", null);
        } else if (e instanceof ParamFlowException) {
            result = new Result<>(102, "热点参数被限流了", null);
        } else if (e instanceof SystemBlockException) {
            result = new Result<>(103, "触发系统保护规则了", null);
        } else if (e instanceof AuthorityException) {
            result = new Result<>(104, "授权规则不通过", null);
        }

        httpServletResponse.setStatus(500);
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(httpServletResponse.getWriter(), result);
    }

}
  
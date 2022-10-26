package com.lemon.learn.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Result<T> implements Serializable {

    private Integer code;

    private String message;

    private T data;


    public Result error(Integer code, String message, T data){
        return new Result(code, message, data);
    }

}

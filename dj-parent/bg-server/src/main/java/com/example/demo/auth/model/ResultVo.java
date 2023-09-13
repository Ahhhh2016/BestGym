package com.example.demo.auth.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ResultVo<T> implements Serializable {

    public static final long serialVersionUID = 1L;

    /**
     * 验证码错误
     */
    public static final int INVALID_VALIDATE_CODE_ERROR = 478;

    /**
     * 验证码过期错误
     */
    public static final int VALIDATE_CODE_EXPIRED_ERROR = 479;

    /**
     * 手机号已经被注册
     */
    public static final int DUPLICATE_PHONE_NUMBER = 476;


    /**
     * 状态码
     */
    private Integer code = 200;

    private Integer status = 200;


    /**
     * 信息
     */
    private String msg = "success";

    /**
     * 数据对象
     */
    private T data;

    private T otherData;

    public ResultVo(T data) {
        this.data = data;
    }

    public ResultVo(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public ResultVo(String msg, T data) {
        this.msg = msg;
        this.data = data;
    }

    public ResultVo(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultVo(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ResultVo success(T data) {
        return new ResultVo().setCode(200).setData(data);
    }

    public static <T> ResultVo success(T data, String msg, T otherData) {
        return new ResultVo().setCode(200).setData(data).setMsg(msg).setOtherData(otherData);
    }

    public static <T> ResultVo success(T data, String msg) {
        return new ResultVo().setCode(200).setData(data).setMsg(msg);
    }

    public static <T> ResultVo success() {
        return new ResultVo().setCode(200).setStatus(200);
    }


    public static <T> ResultVo errorMsg(String msg, Integer status) {
        return new ResultVo().setMsg(msg).setStatus(status);
    }


    public static <T> ResultVo errorMsg(T data, String msg) {
        return new ResultVo().setData(data).setMsg(msg);
    }

    public static <T> ResultVo errorMsg(T data, String msg, T otherData) {
        return new ResultVo().setData(data).setMsg(msg).setOtherData(otherData);
    }

}

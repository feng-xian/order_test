package com.fx.demo.order.utils;

import java.io.Serializable;

/**
 * @version 1.0.0
 * @ClassName ResponseResult
 * @Author 凤仙
 * @Description 通用返回结果集
 * @createTime 2021-08-25 17:11
 */
public class ResponseResult<T> implements Serializable {

    private static final long serialVersionUID = -6818858887875867536L;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 消息
     */
    private String message;

    /**
     * 返回对象
     */
    private T data;

    public ResponseResult() {
    }

    public ResponseResult(Integer code) {
        this.code = code;
    }

    public ResponseResult(T data) {
        this.code = CodeStatus.OK;
        this.data = data;
    }

    public ResponseResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    /**
     * 通用状态码
     * <p>
     * Description:
     * </p>
     *
     * @author 凤仙
     * @version v1.0.0
     * @date 2021-08-25 17:30
     * @see
     */
    public class CodeStatus {
        /**
         * 请求成功
         */
        public static final int OK = 20000;

        /**
         * 请求失败
         */
        public static final int FAIL = 20002;

        /**
         * 熔断请求
         */
        public static final int BREAKING = 20004;

        /**
         * 非法请求
         */
        public static final int ILLEGAL_REQUEST = 50000;

        /**
         * 非法令牌
         */
        public static final int ILLEGAL_TOKEN = 50008;

        /**
         * 参数异常
         */
        public static final int PARAM_ERROR = 50012;

        /**
         * 令牌已过期
         */
        public static final int TOKEN_EXPIRED = 50014;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

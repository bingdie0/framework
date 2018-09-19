package com.my.framework.common.exception;

import lombok.Data;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-04
 **/
@Data
public class BusinessException extends RuntimeException {

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误描述
     */
    private String description;

    public BusinessException(String description) {
        this.description = description;
    }

    public BusinessException(String code, String message) {
        super(code, new Throwable(message));
    }
}

package com.my.framework.response;

import com.alibaba.fastjson.JSON;
import com.my.framework.exception.BusinessException;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-03
 **/
public class ResponseUtils {

    private static final String SUCCESS_CODE = "0";
    private static final String ERROR_CODE = "-1";

    private static final String SUCCESS_MSG = "success";
    private static final String ERROR_MSG = "时光流逝，让我们静下心来，喝杯茶";

    public static Result getBusinessException(BusinessException exception) {
        return new Result(exception.getCode(), exception.getDescription(), null);
    }

    public static Result getSuccessResult(Object obj) {
        return new Result(SUCCESS_CODE, SUCCESS_MSG, JSON.toJSON(obj));
    }

    public static Result getErrorResult() {
        return new Result(ERROR_CODE, ERROR_MSG, null);
    }
}

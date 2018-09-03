package com.my.framework.response;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-03
 **/
public class ResponseUtils {

    private static final int SUCCESS_CODE = 0;
    private static final int ERROR_CODE = -1;

    private static final String SUCCESS_MSG = "success";

    public static Object getSuccessResult(Object obj) {
        return new Result(SUCCESS_CODE, SUCCESS_MSG, obj);
    }
}

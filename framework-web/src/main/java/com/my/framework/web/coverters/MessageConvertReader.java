package com.my.framework.web.coverters;

import org.springframework.http.HttpInputMessage;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-19
 **/
public interface MessageConvertReader {

    /**
     * messageConvert的readInternal方法回调
     *
     * @param obj          将原始的body内容转为的对象
     * @param inputMessage http信息体
     */
    <T> T afterRead(T obj, HttpInputMessage inputMessage);
}

package com.my.framework.web.coverters;

import org.springframework.http.HttpOutputMessage;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-19
 **/
public interface MessageConvertWriter {

    /**
     * messageConvert将信息写到response前的回调
     */
    <T> void beforeWrite(T obj, HttpOutputMessage outputMessage);
}

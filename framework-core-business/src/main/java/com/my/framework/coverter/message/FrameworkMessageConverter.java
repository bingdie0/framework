package com.my.framework.coverter.message;

import com.alibaba.fastjson.JSON;
import com.my.framework.response.ResponseUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-03
 **/
public class FrameworkMessageConverter<T> extends AbstractHttpMessageConverter<T> {
    @Override
    protected boolean supports(Class clazz) {
        return false;
    }

    @Override
    protected T readInternal(Class clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    protected void writeInternal(T o, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
//        ResponseUtils.getSuccessResult(o);
        System.out.println(1);
    }
}

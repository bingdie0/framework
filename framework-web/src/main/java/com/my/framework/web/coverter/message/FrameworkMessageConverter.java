package com.my.framework.web.coverter.message;

import com.alibaba.fastjson.JSON;
import com.my.framework.web.response.ResponseUtils;
import com.my.framework.web.response.Result;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-03
 **/
public class FrameworkMessageConverter<T> extends AbstractHttpMessageConverter<T> {
    @Override
    protected boolean supports(Class clazz) {
        return true;
    }

    @Override
    protected T readInternal(Class clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        String postBodyStr = IOUtils.toString(inputMessage.getBody(), "UTF-8");
        return (T) JSON.parseObject(postBodyStr, clazz);
    }

    @Override
    protected void writeInternal(T o, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        HttpHeaders headers = outputMessage.getHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));

        Result result;
        if (o instanceof Exception) {
            result = ResponseUtils.getErrorResult(o);
        } else {
            result = ResponseUtils.getSuccessResult(o);
        }

        String json = JSON.toJSONString(result);
        OutputStream out = outputMessage.getBody();
        out.write(json.getBytes());
    }
}

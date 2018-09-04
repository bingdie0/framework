package com.my.framework.coverter.message;

import com.alibaba.fastjson.JSON;
import com.my.framework.response.ResponseUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

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
        Charset charset = getContentTypeCharset(inputMessage.getHeaders().getContentType());
        return (T) StreamUtils.copyToString(inputMessage.getBody(), charset);
    }

    @Override
    protected void writeInternal(T o, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        Object result = ResponseUtils.getSuccessResult(o);
        String json = JSON.toJSONString(result);
        OutputStream out = outputMessage.getBody();
        out.write(json.getBytes());
    }

    private Charset getContentTypeCharset(@Nullable MediaType contentType) {
        if (contentType != null && contentType.getCharset() != null) {
            return contentType.getCharset();
        }
        else {
            Charset charset = getDefaultCharset();
            Assert.state(charset != null, "No default charset");
            return charset;
        }
    }
}

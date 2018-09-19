package com.my.framework.web.coverters;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.my.framework.web.response.ResponseUtils;
import com.my.framework.web.response.Result;
import lombok.Data;
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
import java.util.List;
import java.util.Map;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-03
 **/
@Data
public class FrameworkMessageConverter extends AbstractHttpMessageConverter<Object> {

    /**
     * 设置fastJson转换特性
     */
    private SerializerFeature[] features;

    /**
     * xss防御路径
     */
    private List<String> skipXssDefensePath;

    /**
     * 跳过xss的字段
     */
    private Map<String, String> richTextField;

    /**
     * 跳过返回路径
     */
    private List<String> skipReturnResultPathList;

    /**
     * read方法的回调
     */
    private MessageConvertReader messageConvertReader;

    /**
     * write方法的回调
     */
    private MessageConvertWriter messageConvertWriter;

    @Override
    protected boolean supports(Class clazz) {
        return true;
    }

    @Override
    protected Object readInternal(Class clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        String postBodyStr = IOUtils.toString(inputMessage.getBody(), "UTF-8");
        return JSON.parseObject(postBodyStr, clazz);
    }

    @Override
    protected void writeInternal(Object o, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        HttpHeaders headers = outputMessage.getHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));

        Result result = ResponseUtils.getSuccessResult(o);
        String json = JSON.toJSONString(result);
        OutputStream out = outputMessage.getBody();
        out.write(json.getBytes());
    }
}

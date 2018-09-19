package com.my.framework.boot.web.prop;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.my.framework.web.coverters.MessageConvertReader;
import com.my.framework.web.coverters.MessageConvertWriter;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.alibaba.fastjson.serializer.SerializerFeature.*;
import static com.alibaba.fastjson.serializer.SerializerFeature.WriteDateUseDateFormat;
import static com.alibaba.fastjson.serializer.SerializerFeature.WriteNullBooleanAsFalse;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-19
 **/
@Data
@ConfigurationProperties(prefix = "my.mvc.converters")
public class MessageConverterProperties {
    /**
     * 默认的fastJson功能
     */
    public static final SerializerFeature[] DEFAULT_SERIALIZER_FEATURE = {
            QuoteFieldNames,
            WriteMapNullValue,
            WriteNullListAsEmpty,
            WriteNullStringAsEmpty,
            WriteNullBooleanAsFalse,
            WriteDateUseDateFormat
    };

    /**
     *  默认的supportedMediaTypes
     */
    public static final List<MediaType> DEFAULT_MEDIA_TYPE = Arrays.asList(
            MediaType.APPLICATION_JSON_UTF8,
            MediaType.valueOf("text/json;charset=UTF-8"),
            MediaType.valueOf("text/plain;charset=UTF-8")
    );

    /**
     * fastJson转换时的一些功能
     */
    private SerializerFeature[] serializerFeature;

    /**
     * xss防御路径
     */
    private List<String> skipXssDefensePath;

    /**
     * 跳过xss的字段
     */
    private Map<String, String> richTextField;

    /**
     * 支持的media type
     */
    private List<MediaType> supportedMediaTypes;

    /**
     * 跳过返回result的路径
     */
    private List<String> skipReturnResultPathList;

    /**
     * convertReader的类名全路径
     */
    private Class<MessageConvertReader> convertReaderClassName;

    /**
     * convertReader的beanName，需要使用者自己去注册bean，不存在会抛错
     */
    private String convertReaderBeanName;

    /**
     * convertWriter的类名全路径
     */
    private Class<MessageConvertWriter> convertWriterClassName;

    /**
     * convertWriter的beanName，需要使用者自己去注册bean，不存在会抛错
     */
    private String convertWriterBeanName;

    /**
     * 默认跳过返回路径
     */
    private List<String> defaultSkipReturnResultPathList;
}

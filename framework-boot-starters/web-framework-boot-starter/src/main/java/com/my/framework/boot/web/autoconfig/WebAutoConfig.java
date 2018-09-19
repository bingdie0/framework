package com.my.framework.boot.web.autoconfig;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.my.framework.boot.web.prop.MessageConverterProperties;
import com.my.framework.common.exception.BusinessException;
import com.my.framework.web.coverters.FrameworkMessageConverter;
import com.my.framework.web.coverters.MessageConvertReader;
import com.my.framework.web.coverters.MessageConvertWriter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-19
 **/
@Configuration
@ConditionalOnProperty(prefix = "my.mvc", value = "enable", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(value = {
        MessageConverterProperties.class
})
@Slf4j
public class WebAutoConfig implements WebMvcConfigurer, ApplicationContextAware {

    private ApplicationContext applicationContext;


    /**
     * 配置HttpMessageConverters
     */
    @Bean
    @ConditionalOnMissingBean
    public HttpMessageConverters messageConverters(MessageConverterProperties messageConverterProperties) {
        FrameworkMessageConverter messageConverter = new FrameworkMessageConverter();
        // 设置支持的MIME类型
        List<MediaType> supportedMediaTypes = messageConverterProperties.getSupportedMediaTypes();
        if (supportedMediaTypes == null || supportedMediaTypes.size() == 0) {
            supportedMediaTypes = MessageConverterProperties.DEFAULT_MEDIA_TYPE;
        }
        messageConverter.setSupportedMediaTypes(supportedMediaTypes);
        // 设置fastJson转换特性
        SerializerFeature[] serializerFeature = messageConverterProperties.getSerializerFeature();
        if (serializerFeature == null || serializerFeature.length == 0) {
            serializerFeature = MessageConverterProperties.DEFAULT_SERIALIZER_FEATURE;
        }
        messageConverter.setFeatures(serializerFeature);
        // 设置xss过滤
        messageConverter.setSkipXssDefensePath(messageConverterProperties.getSkipXssDefensePath());
        // 设置忽略xss过滤的字段
        messageConverter.setRichTextField(messageConverterProperties.getRichTextField());
        // 设置MessageConvertReader
        setConvertReaderAndWriter(messageConverter, messageConverterProperties);
        List<String> skipReturnResultPathList = messageConverterProperties.getSkipReturnResultPathList();
        if (CollectionUtils.isEmpty(skipReturnResultPathList)) {
            skipReturnResultPathList = new ArrayList<>();
        }
        if (CollectionUtils.isNotEmpty(messageConverterProperties.getDefaultSkipReturnResultPathList())) {
            skipReturnResultPathList.addAll(messageConverterProperties.getDefaultSkipReturnResultPathList());
        }
        messageConverter.setSkipReturnResultPathList(skipReturnResultPathList);
        // 默认会添加spring自带的几个converters
        return new HttpMessageConverters(messageConverter);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 设置convertReader
     *
     * @param messageConverter 消息转换器
     * @param messageConverterProperties 消息转换器配置文件
     */
    private void setConvertReaderAndWriter(FrameworkMessageConverter messageConverter, MessageConverterProperties messageConverterProperties) {

        String readerBeanName = messageConverterProperties.getConvertReaderBeanName();
        Class<MessageConvertReader> readerClassName = messageConverterProperties.getConvertReaderClassName();

        String writerBeanName = messageConverterProperties.getConvertWriterBeanName();
        Class<MessageConvertWriter> writerClassName = messageConverterProperties.getConvertWriterClassName();

        setConvertReader(messageConverter, readerBeanName, readerClassName);
        setConvertWriter(messageConverter, writerBeanName, writerClassName);
    }

    /**
     * 设置convertWriter
     *
     * @param messageConverter 消息转换器
     * @param writerBeanName 写对象name
     * @param writerClassName 写对象类型
     */
    private void setConvertWriter(FrameworkMessageConverter messageConverter, String writerBeanName,
                                  Class<MessageConvertWriter> writerClassName) {
        if (StringUtils.isEmpty(writerBeanName) && writerClassName == null) {
            return;
        }
        if (StringUtils.isNotEmpty(writerBeanName)) {
            try {
                Object bean = applicationContext.getBean(writerBeanName);
                messageConverter.setMessageConvertWriter((MessageConvertWriter) bean);
            } catch (Exception e) {
                log.error("create MessageConvertWriter bean error.", e.getCause());
                throw new BusinessException("创建MessageConvertWriter对象出错");
            }
        } else {
            try {
                MessageConvertWriter convertWriter = writerClassName.newInstance();
                messageConverter.setMessageConvertWriter(convertWriter);
            } catch (Exception e) {
                log.error("create MessageConvertWriter bean error.", e.getCause());
                throw new BusinessException("创建MessageConvertWriter对象出错");
            }
        }
    }

    /**
     * 设置convertReader
     *
     * @param messageConverter 消息转换器
     * @param readBeanName 读对象name
     * @param readClassName 读对象类型
     */
    private void setConvertReader(FrameworkMessageConverter messageConverter, String readBeanName,
                                  Class<MessageConvertReader> readClassName) {
        if (StringUtils.isEmpty(readBeanName) && readClassName == null) {
            return;
        }
        if (StringUtils.isNotEmpty(readBeanName)) {
            try {
                Object bean = applicationContext.getBean(readBeanName);
                messageConverter.setMessageConvertReader((MessageConvertReader) bean);
            } catch (Exception e) {
                log.error("create MessageConvertReader bean error.", e.getCause());
                throw new BusinessException("创建MessageConvertReader对象出错");
            }
        } else {
            try {
                MessageConvertReader convertReader = readClassName.newInstance();
                messageConverter.setMessageConvertReader(convertReader);
            } catch (Exception e) {
                log.error("create MessageConvertReader bean error.", e.getCause());
                throw new BusinessException("创建MessageConvertReader对象出错");
            }
        }
    }
}

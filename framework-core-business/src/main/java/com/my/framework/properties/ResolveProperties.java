package com.my.framework.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-05
 **/
@Component
@org.springframework.context.annotation.PropertySource(name = "error.properties", value = "classpath:error.properties",
        ignoreResourceNotFound = true)
public class ResolveProperties {

    @Autowired
    private Environment environment;


    @SuppressWarnings("unchecked")
    public Map<String, String> getProperties(String propertiesName) {
        MutablePropertySources propertySources = ((StandardEnvironment) environment).getPropertySources();
        PropertySource<?> propertySource = propertySources.get(propertiesName);
        if (Objects.nonNull(propertySource)) {
            return (Map<String, String>) propertySource.getSource();
        }
        return new HashMap<>();
    }

    public String getValue(String key, String propertiesName) {
        return getProperties(propertiesName).get(key);
    }
}

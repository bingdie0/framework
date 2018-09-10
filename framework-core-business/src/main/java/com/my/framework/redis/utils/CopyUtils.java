package com.my.framework.redis.utils;

import com.my.framework.redis.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-05
 **/
@Slf4j
public class CopyUtils {

    public static <T> T copyObject(Object source, Class<T> targetClass) {
        try {
            T target = targetClass.newInstance();
            BeanUtils.copyProperties(source, target);
            // TODO: 2018/9/5 此处先这么写，以后改正
            return (T) source;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BusinessException("F_1002", String.format("Cannot instantiate an object of %s.", targetClass));
        }
    }

    public static<T> List<T> copyList(List<Object> sourceList, Class<T> targetElementClass) {
        List<T> targetList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(sourceList)) {
            sourceList.forEach(source -> {
                T target = copyObject(source, targetElementClass);
                targetList.add(target);
            });
        }
        return targetList;
    }

    public static void main(String[] args) {
        List<Object> sourceList = new ArrayList<>();
        sourceList.add("1");
        sourceList.add("2");
        List<String> targetList = copyList(sourceList, String.class);
        System.out.println(targetList.size());
        targetList.forEach(System.out::println);
        for (int i = 0; i < targetList.size(); i++) {
            System.out.println(targetList.get(i));
        }
        System.out.println(3);
    }
}

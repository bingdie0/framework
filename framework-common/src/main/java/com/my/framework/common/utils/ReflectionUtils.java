package com.my.framework.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;

import java.lang.reflect.Field;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-17
 **/
@Slf4j
public class ReflectionUtils {

    /**
     * 直接读取对象属性值, 无视private/protected修饰符, 不经过getter函数.
     */
    public static Object getFieldValue(Object obj, String fieldName) {
        Field field = getAccessibleField(obj, fieldName);
        if (field == null) {
            throw new IllegalArgumentException("Could not find field [" + fieldName +"] on target [" + obj + "]");
        } else {
            Object result = null;

            try {
                result = field.get(obj);
            } catch (IllegalAccessException e) {
                log.error("不可能抛出的异常！", e);
            }
            return result;
        }
    }

    /**
     * 循环向上转型, 获取对象的DeclaredField, 并强制设置为可访问.
     *
     * 如向上转型到Object仍无法找到, 返回null.
     */
    public static Field getAccessibleField(Object obj, String fieldName) {
        Validate.notNull(obj, "object can\'t be null");
        Validate.notBlank(fieldName, "fieldName can\'t be blank");
        Class superClass = obj.getClass();

        while (superClass != Object.class) {
            try {
                Field field = superClass.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field;
            } catch (NoSuchFieldException e) {
                superClass = superClass.getSuperclass();
            }
        }

        return null;
    }
}

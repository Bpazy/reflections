package com.github.bpazy.reflections;

import lombok.SneakyThrows;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ziyuan
 */
public class Reflections {
    public static Set<Field> getAllFields(Class clazz, Class<? extends Annotation> type) {
        Field[] declaredFields = clazz.getDeclaredFields();
        return Stream.of(declaredFields)
                .filter(field -> field.isAnnotationPresent(type))
                .collect(Collectors.toSet());
    }

    @SneakyThrows
    public static void setField(Object instance, String fieldName, String value) {
        Field field = instance.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(instance, value);
    }
}

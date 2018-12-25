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
    public static Set<Field> getAnnotatedFields(Class clazz, Class<? extends Annotation> type) {
        return getFields(clazz).stream()
                .filter(field -> field.isAnnotationPresent(type))
                .collect(Collectors.toSet());
    }

    public static Set<Field> getFields(Class clazz) {
        Field[] declaredFields = clazz.getDeclaredFields();
        return Stream.of(declaredFields)
                .collect(Collectors.toSet());
    }

    @SneakyThrows
    public static void setField(Object instance, String fieldName, Object value) {
        Field field = instance.getClass().getDeclaredField(fieldName);
        setField(instance, field, value);
    }

    @SneakyThrows
    public static void setField(Object instance, Field field, Object value) {
        field.setAccessible(true);
        field.set(instance, value);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Annotation> T getAnnotation(Class clazz, Class<T> annotation) {
        return (T) clazz.getAnnotation(annotation);
    }
}

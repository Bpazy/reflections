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
    /**
     * get all annotated fields in class.
     *
     * @param clazz which class
     * @param type  annotation type
     * @return set with all annotated fields
     */
    public static Set<Field> getAnnotatedFields(Class clazz, Class<? extends Annotation> type) {
        return getFields(clazz).stream()
                .filter(field -> field.isAnnotationPresent(type))
                .collect(Collectors.toSet());
    }

    /**
     * get all fields in class.
     *
     * @param clazz which class
     * @return set collection with all fields
     */
    public static Set<Field> getFields(Class clazz) {
        Field[] declaredFields = clazz.getDeclaredFields();
        return Stream.of(declaredFields)
                .filter(field -> !field.isSynthetic())
                .collect(Collectors.toSet());
    }

    /**
     * Set class instance value by field name.
     *
     * @param instance  which class's instance
     * @param fieldName which field name you want to set
     * @param value     the value you want to set
     */
    @SneakyThrows
    public static void setField(Object instance, String fieldName, Object value) {
        Field field = instance.getClass().getDeclaredField(fieldName);
        setField(instance, field, value);
    }

    /**
     * Set class instance value by field name.
     *
     * @param instance which class's instance
     * @param field    which field you want to set
     * @param value    the value you want to set
     */
    @SneakyThrows
    public static void setField(Object instance, Field field, Object value) {
        field.setAccessible(true);
        field.set(instance, value);
    }

    /**
     * Get class level annotation.
     *
     * @param clazz      which class
     * @param annotation which annotation you want to get
     * @return annotation instance
     */
    @SuppressWarnings("unchecked")
    public static <T extends Annotation> T getAnnotation(Class clazz, Class<T> annotation) {
        return (T) clazz.getAnnotation(annotation);
    }
}

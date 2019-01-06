package com.github.bpazy.reflections;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author ziyuan
 */
class ReflectionsTest {

    @Test
    void getAnnotatedFields() {
        Set<Field> annotatedFields = Reflections.getAnnotatedFields(TestBean.class, FieldAnnotation.class);
        annotatedFields.forEach(f -> assertEquals(f.getType(), String.class));
    }

    @Test
    void getFields() {
        Set<Field> fields = Reflections.getFields(TestBean.class);
        assertEquals(fields.size(), 1);
    }

    @Test
    void setFieldByFieldName() {
        TestBean bean = new TestBean();
        Reflections.setField(bean, "content", TestConstants.injectedValue);
        assertEquals(TestConstants.injectedValue, bean.getContent());
    }

    @Test
    void setFieldByField() {
        TestBean bean = new TestBean();
        Set<Field> annotatedFields = Reflections.getAnnotatedFields(TestBean.class, FieldAnnotation.class);
        for (Field field : annotatedFields) {
            Reflections.setField(bean, field, TestConstants.injectedValue);
            assertEquals(TestConstants.injectedValue, bean.getContent());
        }
    }

    @Test
    void getAnnotation() {
        ClassAnnotation fieldAnnotation = Reflections.getAnnotation(TestBean.class, ClassAnnotation.class);
        assertEquals(fieldAnnotation.value(), "");
    }
}
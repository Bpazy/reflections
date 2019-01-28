package com.github.bpazy.reflections;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author ziyuan
 */
public class ReflectionsTest {

    @Test
    public void testGetAnnotatedFields() {
        Set<Field> annotatedFields = Reflections.getAnnotatedFields(TestBean.class, FieldAnnotation.class);
        annotatedFields.forEach(f -> assertEquals(f.getType(), String.class));
    }

    @Test
    public void testGetFields() {
        Set<Field> fields = Reflections.getFields(TestBean.class);
        System.out.println(fields);
        assertEquals(fields.size(), 1);
    }

    @Test
    public void testSetFieldByFieldName() {
        TestBean bean = new TestBean();
        Reflections.setField(bean, "content", TestConstants.injectedValue);
        assertEquals(TestConstants.injectedValue, bean.getContent());
    }

    @Test
    public void testSetFieldByField() {
        TestBean bean = new TestBean();
        Set<Field> annotatedFields = Reflections.getAnnotatedFields(TestBean.class, FieldAnnotation.class);
        for (Field field : annotatedFields) {
            Reflections.setField(bean, field, TestConstants.injectedValue);
            assertEquals(TestConstants.injectedValue, bean.getContent());
        }
    }

    @Test
    public void testGetAnnotation() {
        ClassAnnotation fieldAnnotation = Reflections.getAnnotation(TestBean.class, ClassAnnotation.class);
        assertEquals(fieldAnnotation.value(), TestConstants.classAnnotationValue);
    }
}
package com.github.bpazy.reflections;

import lombok.Data;

/**
 * @author ziyuan
 */
@Data
@ClassAnnotation(TestConstants.classAnnotationValue)
public class TestBean {
    @FieldAnnotation(TestConstants.fieldAnnotationValue)
    private String content;
}

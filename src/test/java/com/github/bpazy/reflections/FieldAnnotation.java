package com.github.bpazy.reflections;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author ziyuan
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldAnnotation {
    String value();
}

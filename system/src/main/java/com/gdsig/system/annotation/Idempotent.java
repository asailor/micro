package com.gdsig.system.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author : xs
 * @date : 2023-08-17 10:48
 **/

@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Idempotent {

    /**
     * 参数名，表示将从哪个参数中获取属性值。
     * 获取到的属性值将作为KEY。
     *
     * @return String
     */
    String name() default "";

    /**
     * 属性，表示将获取哪个属性的值。
     *
     * @return String
     */
    String field() default "";

    /**
     * 参数类型
     *
     * @return Class
     */
    Class type();

}

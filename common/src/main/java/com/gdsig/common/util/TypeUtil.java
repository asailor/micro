package com.gdsig.common.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 对多个泛型的复杂Java类进行包装
 *
 * @author huihu
 * @date 2021-08-23
 */
public class TypeUtil {

    /**
     * 对多个泛型的复杂Java类进行包装
     * 案例：List<Map<String,String>> = type(List.class, type(Map.class,String.class,String.class))
     *
     * @param raw  外层Java对象，如List，Map等
     * @param args 内层Java对象，如String，Object等
     * @return 返回参数化Type
     */
    public static Type type(final Class<?> raw, final Type... args) {
        return new ParameterizedType() {
            @Override
            public Type getRawType() {
                return raw;
            }

            @Override
            public Type[] getActualTypeArguments() {
                return args;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }
        };
    }

}

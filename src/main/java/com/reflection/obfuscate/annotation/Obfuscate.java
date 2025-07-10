package com.reflection.obfuscate.annotation;

import com.reflection.obfuscate.enums.ObfuscateStrategy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Obfuscate {
    public ObfuscateStrategy obfuscateStrategy();
    public int obfuscateLength() default 2;
}

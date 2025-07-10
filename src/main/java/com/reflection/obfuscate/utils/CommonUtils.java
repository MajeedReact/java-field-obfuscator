package com.reflection.obfuscate.utils;

import com.reflection.obfuscate.annotation.Obfuscate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

public class CommonUtils {

   public static final Logger logger = LoggerFactory.getLogger(CommonUtils.class);

    /**
     * This method takes a field of type String and obfuscates its value based on the length specified in the Obfuscate annotation.
     * @param fieldType field type
     * @param field field itself
     * @param instance instance of the class containing the field
     * @return obfuscated value of the field
     * @throws IllegalAccessException
     */
    public static String obfuscateValues(Class<?> fieldType, Field field, Object instance) throws IllegalAccessException {
        if(!fieldType.equals(String.class))
            throw new RuntimeException("Only String classes supported");

        field.setAccessible(true);
        String value = (String) field.get(instance);
        int lengthOfObfuscation = field.getAnnotation(Obfuscate.class).obfuscateLength();

        if(value == null || value.length() <= lengthOfObfuscation)
            return value;

        StringBuilder obfuscatedValue = new StringBuilder();
        for(int i=0; i < value.length()-lengthOfObfuscation; i++){
            obfuscatedValue.append("*");
        }

        obfuscatedValue.append(value.substring(obfuscatedValue.length()));

        return obfuscatedValue.toString();
    }
}

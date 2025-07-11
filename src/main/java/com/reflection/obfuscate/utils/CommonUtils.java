package com.reflection.obfuscate.utils;

import com.reflection.obfuscate.annotation.Obfuscate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class CommonUtils {

    public static final Logger logger = LoggerFactory.getLogger(CommonUtils.class);

    /**
     * This method takes a field of type String and obfuscates its value based on the length specified in the Obfuscate annotation.
     * @return obfuscated value of the field
     * @throws IllegalAccessException
     */
    public static void obfuscateValues(Object user) throws IllegalAccessException {
        //Filter out sensitive fields to be obfuscated
        List<Field> sensitiveFields = Arrays.stream(user.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Obfuscate.class)).toList();

        for (Field field : sensitiveFields) {
            if (!field.getType().equals(String.class))
                throw new RuntimeException("Only String classes supported");

            field.setAccessible(true);
            String value = (String) field.get(user);
            int lengthOfObfuscation = field.getAnnotation(Obfuscate.class).obfuscateLength();

            if (value == null || value.length() <= lengthOfObfuscation)
                continue;

            StringBuilder obfuscatedValue = new StringBuilder();
            for (int i = 0; i < value.length() - lengthOfObfuscation; i++) {
                obfuscatedValue.append("*");
            }

            obfuscatedValue.append(value.substring(obfuscatedValue.length()));

            field.set(user, obfuscatedValue.toString());
        }
    }
}

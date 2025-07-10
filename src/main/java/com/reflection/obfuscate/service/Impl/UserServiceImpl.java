package com.reflection.obfuscate.service.Impl;

import com.reflection.obfuscate.annotation.Obfuscate;
import com.reflection.obfuscate.dto.response.UserDTO;
import com.reflection.obfuscate.service.UserService;
import com.reflection.obfuscate.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public UserDTO getUserDetails() {
        UserDTO user = UserDTO.builder()
                .fullName("John Doe")
                .nationalId("1234567890")
                .mobileNumber("966531234212")
                .createdDate(LocalDateTime.now())
                .build();

        //Filter out sensitive fields to be obfuscated
        List<Field> sensitiveFields = Arrays.stream(user.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Obfuscate.class)).toList();

        for(Field field : sensitiveFields){
            try {
                field.set(user, CommonUtils.obfuscateValues(field.getType(), field, user));
            } catch (IllegalAccessException e) {
                logger.error("Cannot access fields value for the field name: {}", field.getName());
            }
        }

        return user;
    }
}

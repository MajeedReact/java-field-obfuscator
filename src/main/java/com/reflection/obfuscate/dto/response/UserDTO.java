package com.reflection.obfuscate.dto.response;

import com.reflection.obfuscate.annotation.Obfuscate;
import com.reflection.obfuscate.enums.ObfuscateStrategy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Data
public class UserDTO {
    private String fullName;
    @Obfuscate(obfuscateStrategy = ObfuscateStrategy.NATIONAL_ID)
    private String nationalId;
    @Obfuscate(obfuscateStrategy = ObfuscateStrategy.MOBILE_NUMBER, obfuscateLength = 4)
    private String mobileNumber;
    private LocalDateTime createdDate;

}

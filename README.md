# Java field obfuscator
This is a demo project of an obfuscation algorithm for sensitive field values exposed through REST APIs, utilizing Java Reflection with a custom annotation.

## Sensetive fields:
<img width="753" height="312" alt="image" src="https://github.com/user-attachments/assets/040697f2-9306-4da2-9d3d-5eae6de8472e" />

## Annotation obfuscate:
Highlights the fields to be obfuscated dynamically by the algorithm.

### Annotation property:
- **Obfuscate length:** The number of characters to be shown at the end of the string. `Default 2`
- **ObfuscateStrategy:** This is an enum value that can extend the algorithm to perform additional obfuscation with regular expressions or any modification needed by identifying the strategy.


## Returned Payload when the DTO is invoked through the algorithm:
```
{
"fullName": "John Doe",
"nationalId": "********90",
"mobileNumber": "********4212",
"createdDate": "2025-07-10T19:44:30.7260185"
}
```

## Libraries used:
- Spring Boot starter web.
- Lombok.
- Java Reflection.

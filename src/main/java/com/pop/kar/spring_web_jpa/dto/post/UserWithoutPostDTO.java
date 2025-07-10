package com.pop.kar.spring_web_jpa.dto.post;

import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString

public class UserWithoutPostDTO {
    private final Integer id;
    private final String login;
    private final String displayName;
    private final Integer yearOfBirth;
}

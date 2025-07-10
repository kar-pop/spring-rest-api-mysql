package com.pop.kar.spring_web_jpa.dto.post;

import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString

public class PostDTO {
    private final Integer id;
    private final String body;
    private final UserWithoutPostDTO user;
}

package com.pop.kar.spring_web_jpa.dto.user;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString

public class PostWithoutUserDTO {
    private Integer id;
    private String body;
}

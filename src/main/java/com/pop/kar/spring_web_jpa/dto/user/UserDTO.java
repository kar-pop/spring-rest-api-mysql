package com.pop.kar.spring_web_jpa.dto.user;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserDTO {

    private final Integer id;
    private final String login;
    private final List<PostWithoutUserDTO> posts;
}

package com.pop.kar.spring_web_jpa.dto.post;

import com.pop.kar.spring_web_jpa.entitiy.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserWithoutPostDTOMapper implements Function<User, UserWithoutPostDTO> {

    @Override
    public UserWithoutPostDTO apply(User user) {
        return new UserWithoutPostDTO(
                user.getId(),
                user.getLogin(),
                user.getDisplayName(),
                user.getYearOfBirth());
    }
}

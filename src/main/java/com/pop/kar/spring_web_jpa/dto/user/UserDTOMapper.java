package com.pop.kar.spring_web_jpa.dto.user;

import com.pop.kar.spring_web_jpa.entitiy.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserDTOMapper implements Function<User, UserDTO> {

    private final PostWithoutUserDTOMapper postWithoutUserDTOMapper;

    public UserDTOMapper(PostWithoutUserDTOMapper postWithoutUserDTOMapper) {
        this.postWithoutUserDTOMapper = postWithoutUserDTOMapper;
    }

    @Override
    public UserDTO apply(User user) {
        return new UserDTO(
                user.getId(),
                user.getLogin(),
                user.getPosts()
                        .stream()
                        .map(postWithoutUserDTOMapper)
                        .collect(Collectors.toList())
        );
    }
}

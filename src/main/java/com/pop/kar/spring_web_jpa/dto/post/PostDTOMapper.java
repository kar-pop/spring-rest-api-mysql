package com.pop.kar.spring_web_jpa.dto.post;

import com.pop.kar.spring_web_jpa.entitiy.Post;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PostDTOMapper implements Function<Post, PostDTO> {

    public final UserWithoutPostDTOMapper userWithoutPostDTOMapper;

    public PostDTOMapper(UserWithoutPostDTOMapper userWithoutPostDTOMapper) {
        this.userWithoutPostDTOMapper = userWithoutPostDTOMapper;
    }

    @Override
    public PostDTO apply(Post post) {
        return new PostDTO(
                post.getId(),
                post.getBody(),
                userWithoutPostDTOMapper.apply(post.getUser())
        );
    }
}

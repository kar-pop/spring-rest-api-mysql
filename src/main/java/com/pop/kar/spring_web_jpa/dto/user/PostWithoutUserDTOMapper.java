package com.pop.kar.spring_web_jpa.dto.user;

import com.pop.kar.spring_web_jpa.entitiy.Post;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PostWithoutUserDTOMapper implements Function<Post, PostWithoutUserDTO>{

    @Override
    public PostWithoutUserDTO apply(Post post) {
        return new PostWithoutUserDTO(post.getId(), post.getBody());
    }
}

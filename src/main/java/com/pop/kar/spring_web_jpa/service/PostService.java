package com.pop.kar.spring_web_jpa.service;

import com.pop.kar.spring_web_jpa.entitiy.Post;
import com.pop.kar.spring_web_jpa.entitiy.User;
import com.pop.kar.spring_web_jpa.exception.PostNotFoundException;
import com.pop.kar.spring_web_jpa.repository.PostRepository;
import com.pop.kar.spring_web_jpa.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Iterable<Post> findAll(){
        return postRepository.findAll();
    }

    public Post findById(Integer id){
        return postRepository.findById(id)
                .orElseGet(Post::new);
    }

    @Transactional
    public Post save(Post post){
        return postRepository.save(post);
    }

    @Transactional
    public Post update(Integer id, Post post){

        return postRepository.findById(id)
                .map(savedPost -> {
                    savedPost.setBody(post.getBody());
                    savedPost.setUser(post.getUser());
                    return postRepository.save(savedPost);
                })
                .orElseThrow(PostNotFoundException::new);
    }

    @Transactional
    public Post patch(Integer id, Post post){

        return postRepository.findById(id)
                .map(savedPost -> {
                    if(post.getBody() != null) savedPost.setBody(post.getBody());

                    if(post.getUser() != null){
                        Optional<User> savedUser = userRepository.findById(post.getUser().getId());
                        savedUser.ifPresent(savedPost::setUser);
                    }
                    return postRepository.save(savedPost);
                })
                .orElseThrow(PostNotFoundException::new);
    }

    @Transactional
    public void delete(Integer id){

        findById(id);

        postRepository.deleteById(id);
    }

}

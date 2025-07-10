package com.pop.kar.spring_web_jpa.controller;

import com.pop.kar.spring_web_jpa.entitiy.Post;
import com.pop.kar.spring_web_jpa.repository.PostRepository;
import com.pop.kar.spring_web_jpa.entitiy.User;
import com.pop.kar.spring_web_jpa.repository.UserRepository;
import com.pop.kar.spring_web_jpa.dto.post.PostDTO;
import com.pop.kar.spring_web_jpa.dto.post.PostDTOMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class PostController {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostDTOMapper postDTOMapper;

    public PostController(PostRepository postRepository, PostDTOMapper postDTOMapper, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.postDTOMapper = postDTOMapper;
    }

    @GetMapping("posts")
    public ResponseEntity<Iterable<PostDTO>> getAllPost() {
        return ResponseEntity.ok(
                StreamSupport.stream(postRepository.findAll().spliterator(), false)
                        .map(postDTOMapper)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("posts/{id}")
    public ResponseEntity<PostDTO> getOnePost(@PathVariable Integer id){

        return postRepository.findById(id)
                .map(postDTOMapper)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("posts")
    public ResponseEntity<PostDTO> addPost(@RequestBody @Valid Post post) {
        Post savedPost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();

        savedPost.getUser()
                .setLogin(
                        userRepository
                                .findById(savedPost.getUser().getId())
                                .map(User::getLogin)
                                .orElse("No value")
                );

        return ResponseEntity
                .created(location)
                .body(postDTOMapper.apply(savedPost));
    }

    @PutMapping("posts/{id}")
    public ResponseEntity<PostDTO> updateUser(@PathVariable Integer id, @RequestBody @Valid Post post){

        Optional<User> user = userRepository.findById(post.getUser().getId());

        if(user.isEmpty() || post.getBody() == null || post.getBody().isBlank()){
            return ResponseEntity.badRequest().build();
        }

        return postRepository.findById(id)
                .map(savedPost -> {
                    savedPost.setBody(post.getBody());
                    savedPost.setUser(user.get());
                    return postRepository.save(savedPost);
                })
                .map(postDTOMapper)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("posts/{id}")
    public ResponseEntity<PostDTO> patchPost(@PathVariable Integer id, @RequestBody @Valid Post post){

        return postRepository.findById(id)
                .map(savedPost -> {
                     if(post.getBody() != null) savedPost.setBody(post.getBody());

                     if(post.getUser() != null){
                         Optional<User> savedUser = userRepository.findById(post.getUser().getId());
                         savedUser.ifPresent(savedPost::setUser);
                     }

                    return postRepository.save(savedPost);
                })
                .map(postDTOMapper)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("posts/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Integer id){

        if(!postRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        postRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
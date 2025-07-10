package com.pop.kar.spring_web_jpa.controller;

import com.pop.kar.spring_web_jpa.dto.post.UserWithoutPostDTO;
import com.pop.kar.spring_web_jpa.dto.user.UserDTO;
import com.pop.kar.spring_web_jpa.entitiy.User;
import com.pop.kar.spring_web_jpa.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<UserDTO>> getAllUsers() {

        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserWithoutPostDTO> getUser(@PathVariable Integer id) {

        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<UserWithoutPostDTO> addUser(@RequestBody @Valid User user) {
        UserWithoutPostDTO savedUser = userService.save(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(uri).body(savedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserWithoutPostDTO> updateUser(@PathVariable Integer id, @RequestBody @Valid User user) {

        return ResponseEntity.ok(userService.update(id, user));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserWithoutPostDTO> patchUser(@PathVariable Integer id, @RequestBody @Valid User user) {

        return ResponseEntity.ok(userService.patch(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {

        userService.delete(id);

        return ResponseEntity.noContent().build();
    }
}

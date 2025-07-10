package com.pop.kar.spring_web_jpa.service;

import com.pop.kar.spring_web_jpa.dto.post.UserWithoutPostDTO;
import com.pop.kar.spring_web_jpa.dto.post.UserWithoutPostDTOMapper;
import com.pop.kar.spring_web_jpa.dto.user.UserDTO;
import com.pop.kar.spring_web_jpa.dto.user.UserDTOMapper;
import com.pop.kar.spring_web_jpa.entitiy.User;
import com.pop.kar.spring_web_jpa.exeption.UserNotFoundException;
import com.pop.kar.spring_web_jpa.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserWithoutPostDTOMapper userWithoutPostDTOMapper;
    private final UserDTOMapper userDTOMapper;

    public UserService(UserDTOMapper userDTOMapper, UserRepository userRepository, UserWithoutPostDTOMapper userWithoutPostDTOMapper) {
        this.userDTOMapper = userDTOMapper;
        this.userRepository = userRepository;
        this.userWithoutPostDTOMapper = userWithoutPostDTOMapper;
    }

    public Iterable<UserDTO> findAll(){

//        List<User> l = userRepository.findByYearOfBirthBetween(1990, 2000);
//
//        l.forEach(System.out::println);


        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                        .map(userDTOMapper)
                        .collect(Collectors.toList());
    }

    public UserWithoutPostDTO findById(Integer id){

        return userRepository.findById(id)
                .map(userWithoutPostDTOMapper)
                .orElseThrow(() -> new UserNotFoundException("Not found User with this ID"));
    }

    @Transactional
    public UserWithoutPostDTO save(User user){

        return userWithoutPostDTOMapper.apply(userRepository.save(user));
    }

    @Transactional
    public UserWithoutPostDTO update(Integer id, User user){

        return userRepository.findById(id)
                .map(existingUser -> {

                    if (user.getLogin() != null) existingUser.setLogin(user.getLogin());
                    if (user.getDisplayName() != null) existingUser.setDisplayName(user.getDisplayName());
                    if (user.getYearOfBirth() != null) existingUser.setYearOfBirth(user.getYearOfBirth());

                    return userRepository.save(existingUser);
                })
                .map(userWithoutPostDTOMapper)
                .orElseThrow(() -> new UserNotFoundException("Not found User with this ID"));
    }

    @Transactional
    public UserWithoutPostDTO patch(Integer id, User user){

        return userRepository.findById(id)
                .map(existingUser -> {

                    if (user.getLogin() != null) existingUser.setLogin(user.getLogin());
                    if (user.getDisplayName() != null) existingUser.setDisplayName(user.getDisplayName());
                    if (user.getYearOfBirth() != null) existingUser.setYearOfBirth(user.getYearOfBirth());

                    return userRepository.save(existingUser);
                })
                .map(userWithoutPostDTOMapper)
                .orElseThrow(() -> new UserNotFoundException("Not found User with this ID"));
    }

    @Transactional
    public void delete(Integer id){

        findById(id);

        userRepository.deleteById(id);
    }
}

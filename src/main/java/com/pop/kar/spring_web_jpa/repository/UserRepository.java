package com.pop.kar.spring_web_jpa.repository;

import com.pop.kar.spring_web_jpa.entitiy.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {


//    public List<User> findByYearOfBirthBetween(Integer start, Integer end);
}

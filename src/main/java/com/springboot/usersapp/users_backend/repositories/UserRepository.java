package com.springboot.usersapp.users_backend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.usersapp.users_backend.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}

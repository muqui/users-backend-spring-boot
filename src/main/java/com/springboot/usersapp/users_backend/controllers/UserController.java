package com.springboot.usersapp.users_backend.controllers;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.usersapp.users_backend.entities.User;
import com.springboot.usersapp.users_backend.services.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;







@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public List<User>  list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Optional<User> userOptional = service.findById(id);
        if(userOptional.isPresent()){
            return ResponseEntity.ok( userOptional.orElseThrow());
        }
        return ResponseEntity.status(404).body(Collections.singletonMap("error", "usuario no encontrado"));
      
    }
    
    @PostMapping
    public  ResponseEntity<User> create(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
        Optional<User> userOptional = service.findById(id);
        if(userOptional.isPresent()){
           User userDb = userOptional.get();
           userDb.setEmail(user.getEmail());
           user.setLastname(user.getLastname());
           user.setName(user.getName());
           user.setPassword(user.getPassword());
           user.setUsername(user.getUsername());
           return ResponseEntity.ok(service.save(userDb));
        }

        return ResponseEntity.notFound().build();
        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<User> userOptional = service.findById(id);
        if(userOptional.isPresent()){
           service.deleteById(id);
           return ResponseEntity.noContent().build();
         }
         return ResponseEntity.notFound().build();
    }
  

}

package com.example.carros.api.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/info")
    public ResponseEntity<List<User>> getUsers(){
        var users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<?> getUsersId(@PathVariable("id") Long id){
        var user = userRepository.findById(id);
        return ResponseEntity.ok(user);
    }
}

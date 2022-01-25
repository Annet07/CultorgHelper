package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.entity.Response;

import ru.itis.javalab.service.TokenService;
import ru.itis.javalab.service.UserService;

@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @PostMapping("/update")
    public ResponseEntity<Response> updateTokens(@RequestHeader("R-TOKEN") String token){
        return ResponseEntity.ok(tokenService.updateTokenWithRefresh(token));
    }

    @PostMapping("/users/{user-id}/block")
    public ResponseEntity<?> blockUser(@PathVariable("user-id") Long userId) {
        userService.blockUser(userId);
        return ResponseEntity.ok().build();
    }
}

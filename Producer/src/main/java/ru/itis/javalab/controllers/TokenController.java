package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.javalab.entity.Response;
import ru.itis.javalab.service.TokenService;

@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/update")
    public ResponseEntity<Response> updateTokens(@RequestHeader("R-TOKEN") String token){
        return ResponseEntity.ok(tokenService.updateTokenWithRefresh(token));
    }
}

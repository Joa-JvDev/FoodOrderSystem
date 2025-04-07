package jva.dev.foodordersystem.controller;

import jakarta.validation.Valid;
import jva.dev.foodordersystem.dto.request.AuthLoginRequest;
import jva.dev.foodordersystem.dto.request.AuthRegisterRequest;
import jva.dev.foodordersystem.dto.response.AuthResponse;
import jva.dev.foodordersystem.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/log-in")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest userRequest){
        return new ResponseEntity<>(this.userDetailsService.loginUser(userRequest), HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid AuthRegisterRequest authRegisterRequest){
        return new ResponseEntity<>(this.userDetailsService.createUser(authRegisterRequest), HttpStatus.CREATED);
    }

}

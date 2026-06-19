package com.study.bookapi.service;

import com.study.bookapi.dto.LoginRequest;
import com.study.bookapi.dto.RegisterRequest;
import com.study.bookapi.exception.DuplicateRegistrationException;
import com.study.bookapi.exception.InvalidCredentialsException;
import com.study.bookapi.model.User;
import com.study.bookapi.repository.UserRepository;
import com.study.bookapi.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
        this.jwtUtil=jwtUtil;


    }
    public String register(RegisterRequest request){
        if(userRepository.findByUsername(request.username()).isPresent()){
            throw new DuplicateRegistrationException("User already Exists");
        }
        String hashed = passwordEncoder.encode(request.password());
        User user = new User(null,request.username(),hashed);
        userRepository.save(user);
        return jwtUtil.generateToken(user.getUsername());
    }

    public String login(LoginRequest request){
        User user = userRepository.findByUsername(request.username()).orElseThrow(()->new InvalidCredentialsException("Invalid Credentials"));
        if(!passwordEncoder.matches(request.password(), user.getPassword())){
            throw new InvalidCredentialsException("Invalid Credentials");
        }
        return jwtUtil.generateToken(user.getUsername());
    }

}

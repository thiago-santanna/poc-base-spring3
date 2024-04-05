package com.tss.webapps.projectbasepoc.controllers;

import com.tss.webapps.projectbasepoc.domain.user.User;
import com.tss.webapps.projectbasepoc.dto.LoginRequestDto;
import com.tss.webapps.projectbasepoc.dto.RegisterRequestDto;
import com.tss.webapps.projectbasepoc.dto.ResponseDto;
import com.tss.webapps.projectbasepoc.dto.ResponseRegisterDto;
import com.tss.webapps.projectbasepoc.infra.security.TokenService;
import com.tss.webapps.projectbasepoc.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDto login) {
        User user = this.userRepository.findByEmail(login.email())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        if(passwordEncoder.matches(login.password(), user.getPassword())) {
            return ResponseEntity.ok(new ResponseDto(user.getName()));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDto registerDto) {
        Optional<User> user = this.userRepository.findByEmail(registerDto.email());
        if(user.isEmpty()) {
            User newUser = new User(registerDto.name(), registerDto.email(), passwordEncoder.encode(registerDto.password()));
            this.userRepository.save(newUser);
            String generatedToken = tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseRegisterDto(newUser.getName(), generatedToken));
        }

        return ResponseEntity.badRequest().build();
    }
}

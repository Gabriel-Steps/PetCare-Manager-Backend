package com.project.petcaremanager.services;

import com.project.petcaremanager.dtos.userDtos.CreateUserDto;
import com.project.petcaremanager.dtos.userDtos.AccessResponseDto;
import com.project.petcaremanager.dtos.userDtos.LoginUserDto;
import com.project.petcaremanager.models.User;
import com.project.petcaremanager.models.enums.Role;
import com.project.petcaremanager.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService){
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AccessResponseDto create(CreateUserDto dto){
        try{
            Role role = Role.valueOf(dto.role().toUpperCase());
            User user = new User(
                    null,
                    dto.name(),
                    dto.email(),
                    passwordEncoder.encode(dto.password()),
                    role
            );

            repository.save(user);
            String token = jwtService.generateToken(user);

            return new AccessResponseDto(
                    user.getName(),
                    user.getEmail(),
                    token
            );
        }catch (IllegalArgumentException ex) {
            throw new RuntimeException("Role Inválida");
        }
    }

    public AccessResponseDto login(LoginUserDto dto){
        User user = repository.findByEmail(dto.email())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "Email ou senha inválidos"
                ));
        boolean passwordMatches = passwordEncoder.matches(
                dto.password(),
                user.getPassword()
        );

        if (!passwordMatches){
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "E-mail ou senha inválidos"
            );
        }

        String token = jwtService.generateToken(user);

        return new AccessResponseDto(
                user.getName(),
                user.getEmail(),
                token
        );
    }
}

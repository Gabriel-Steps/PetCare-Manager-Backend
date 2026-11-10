package com.todolist.todolist.services;

import com.todolist.todolist.dtos.userDtos.CreateUserDto;
import com.todolist.todolist.dtos.userDtos.GetUsersDto;
import com.todolist.todolist.dtos.userDtos.AccessResponseDto;
import com.todolist.todolist.dtos.userDtos.LoginUserDto;
import com.todolist.todolist.models.User;
import com.todolist.todolist.models.enums.Role;
import com.todolist.todolist.repositories.UserRepository;
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
        User user = new User(
                null,
                dto.name(),
                dto.email(),
                passwordEncoder.encode(dto.password()),
                Role.USER
        );

        repository.save(user);
        String token = jwtService.generateToken(user);

        return new AccessResponseDto(
                user.getName(),
                user.getEmail(),
                token
        );
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

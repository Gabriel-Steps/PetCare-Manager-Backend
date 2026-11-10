package com.todolist.todolist.services;

import com.todolist.todolist.dtos.userDtos.CreateUserDto;
import com.todolist.todolist.dtos.userDtos.GetUsersDto;
import com.todolist.todolist.dtos.userDtos.UpdateUserDto;
import com.todolist.todolist.exceptions.ResourceNotFoundException;
import com.todolist.todolist.models.User;
import com.todolist.todolist.models.enums.Role;
import com.todolist.todolist.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository repository, PasswordEncoder passwordEncoder){
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<GetUsersDto> findAll() {
        return repository.findAll()
                .stream()
                .map(user ->
                    new GetUsersDto(
                            user.getId(),
                            user.getName(),
                            user.getEmail()
                    )).toList();
    }

    public GetUsersDto findById(Long id){
        User user =  repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum usuário encontrado com o id " + id));

        return new GetUsersDto(
                user.getId(),
                user.getName(), user.getEmail()
        );
    }

    public void delete(Long id){
        User user = repository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Nenhum usuário com esse id " + id
                        ));

        repository.deleteById(id);
    }

    public GetUsersDto update(Long id, UpdateUserDto dto) {
        User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Nenhum usuário encontrado com esse id " + id
                ));
        user.setName(dto.name());
        user.setEmail(dto.email());

        repository.save(user);

        return new GetUsersDto(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}

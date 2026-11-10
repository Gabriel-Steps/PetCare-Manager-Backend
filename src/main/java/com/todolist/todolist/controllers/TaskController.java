package com.todolist.todolist.controllers;

import com.todolist.todolist.dtos.ResponseApiDto;
import com.todolist.todolist.dtos.taskDtos.CreateTaskDto;
import com.todolist.todolist.dtos.taskDtos.GetTasksByCompleted;
import com.todolist.todolist.dtos.taskDtos.ResponseTaskDto;
import com.todolist.todolist.dtos.taskDtos.UpdateTaskDto;
import com.todolist.todolist.models.Task;
import com.todolist.todolist.services.TaskService;
import org.springframework.security.oauth2.jwt.Jwt;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service){
        this.service = service;
    }

//    @GetMapping
//    public ResponseEntity<ResponseApiDto<List<Task>>> findAll(){
//
//        List<Task> tasks = service.findAll();
//
//        return ResponseEntity.ok(
//                new ResponseApiDto<>(true, null, tasks)
//        );
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseApiDto<Void> create(@AuthenticationPrincipal Jwt jwt,@RequestBody @Valid CreateTaskDto dto){
        Long userId = jwt.getClaim("userId");
        service.create(userId, dto);
        return new ResponseApiDto<>(true, "Tarefa criada com sucesso!", null);
    }

    @GetMapping
    public ResponseEntity<ResponseApiDto<Page<ResponseTaskDto>>> findAllPage(@AuthenticationPrincipal Jwt jwt, Pageable pageable){
        Long userId = jwt.getClaim("userId");
        return ResponseEntity.ok(
                new ResponseApiDto<>(
                        true,
                        null,
                        service.findAllPage(userId, pageable))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseApiDto<ResponseTaskDto>> findById(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Long id){
        Long userId = jwt.getClaim("userId");
        return ResponseEntity.ok(
                new ResponseApiDto<>(true, null, service.findById(userId, id))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseApiDto<Void>> update(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Long id,
            @RequestBody @Valid UpdateTaskDto dto
    ){
        Long userId = jwt.getClaim("userId");
        if (id <= 0){
            return ResponseEntity.badRequest().build();
        }
        service.update(userId, id, dto);
        return ResponseEntity.ok(
                new ResponseApiDto<>(true, "Tarefa atualizada com sucesso", null)
        );
    }

    @PutMapping("/{id}/completed")
    public ResponseEntity<ResponseApiDto<Void>> completeTask(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Long id
    ){
        Long userId = jwt.getClaim("userId");
        service.completeTask(userId, id);
        return ResponseEntity.ok(
                new ResponseApiDto<>(true, "Tarefa completado com sucesso", null)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseApiDto<Void>> delete(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Long id
    ){
        Long userId = jwt.getClaim("userId");
        service.delete(userId, id);
        return ResponseEntity.ok(
                new ResponseApiDto<>(true, "Tarefa deletada com sucesso", null)
        );
    }

    @GetMapping("/completeds")
    public ResponseEntity<ResponseApiDto<Page<ResponseTaskDto>>> findAllByCompleted(
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody @Valid GetTasksByCompleted getTasksByCompleted,
            Pageable pageable
            ){
        Long userId = jwt.getClaim("userId");
        return ResponseEntity.ok(
                new ResponseApiDto<>(true, null, service.findAllByCompleted(userId, getTasksByCompleted.status(), pageable))
        );
    }

    @GetMapping("/user/{id}") // Uso somente para teste
    public ResponseEntity<ResponseApiDto<List<Task>>> findAllByUser(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(
                new ResponseApiDto<>(true, null, service.findAllByUser(id))
        );
    }
}

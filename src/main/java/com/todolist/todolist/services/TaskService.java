package com.todolist.todolist.services;

import com.todolist.todolist.dtos.taskDtos.CreateTaskDto;
import com.todolist.todolist.dtos.taskDtos.ResponseTaskDto;
import com.todolist.todolist.dtos.taskDtos.UpdateTaskDto;
import com.todolist.todolist.exceptions.ResourceNotFoundException;
import com.todolist.todolist.models.Task;
import com.todolist.todolist.models.User;
import com.todolist.todolist.repositories.TaskRepository;
import com.todolist.todolist.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository){
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    public void create(Long id, CreateTaskDto dto){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Nenhum usuário encontrado com o id " + id
                ));

        Task task = new Task(
                null,
                dto.title(),
                dto.description(),
                user
        );

        taskRepository.save(task);
    }

    public Page<ResponseTaskDto> findAllPage(Long userId, Pageable pageable) {
        return taskRepository.findAllByUserId(userId, pageable)
                .map(task -> new ResponseTaskDto(
                        task.getId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.isCompleted()
                ));
    }

    public ResponseTaskDto findById(Long userId, Long id){
        Task task = taskRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Nenhuma tarefa encontrada com esse id " + id
                ));

        return new ResponseTaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.isCompleted()
        );
    }

    public void update(Long userId,Long id, UpdateTaskDto dto){
        Task task = taskRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Tarefa não encontrada"
                ));

        task.setTitle(dto.title());
        task.setDescription(dto.description());

        taskRepository.save(task);
    }

    public void completeTask(Long userId, Long id){
        Task task = taskRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Tarefa não encontrada"
                ));

        task.setCompleted(true);
        task.setCompletionDate(LocalDate.now());

        taskRepository.save(task);
    }

    public void delete(Long userId, Long id){
        Task task = taskRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Tarefa não encontrada"
                ));

        taskRepository.delete(task);
    }

    public Page<ResponseTaskDto> findAllByCompleted(Long userId, boolean completed, Pageable pageable){
        return taskRepository.findAllByCompletedAndUserId(completed, userId, pageable)
                .map(task -> new ResponseTaskDto(
                        task.getId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.isCompleted()
                ));
    }

    public List<Task> findAllByUser(Long id){
        return taskRepository.findAll()
                .stream()
                .filter(task -> task.getUser().getId().equals(id)).toList();
    }
}

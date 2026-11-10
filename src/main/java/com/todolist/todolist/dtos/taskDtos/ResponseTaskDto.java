package com.todolist.todolist.dtos.taskDtos;

public record ResponseTaskDto (Long id, String title, String description, boolean completed){
}

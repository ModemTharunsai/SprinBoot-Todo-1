package com.example.todo.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.todo.service.TodoH2Service;
import java.util.*;
import java.util.ArrayList;
 import org.springframework.beans.factory.annotation.Autowired;
import java.util.Map;
import com.example.todo.model.Todo;
@RestController
class TodoController {
    @Autowired
    public TodoH2Service todoService;

    @GetMapping("/todos")
    public ArrayList<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    @GetMapping("/todos/{todoId}")
    public Todo getTodoById(@PathVariable int todoId) {
        return todoService.getTodoById(todoId);
    }

    @PostMapping("/todos")
    public Todo createTodo(@RequestBody Todo todo) {
     return todoService.createTodo(
                todo.getTodo(),
                todo.getPriority(),
                todo.getStatus()
        );
    }

    @PutMapping("/todos/{todoId}")
    public Todo updateTodo(@PathVariable int todoId, @RequestBody Todo updatedTodo) {
        return todoService.updateTodoStatus(todoId, updatedTodo.getStatus());
    }

    @DeleteMapping("/todos/{todoId}")
    public void deleteTodo(@PathVariable int todoId) {
        todoService.deleteTodoById(todoId);
    }
}
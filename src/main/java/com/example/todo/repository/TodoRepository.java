package com.example.todo.repository;
import java.util.*;
import com.example.todo.model.Todo;
public interface TodoRepository {
    ArrayList<Todo> getAllTodos();
    Todo getTodoById(int id);
    Todo createTodo(String todo, String priority, String status);
    Todo updateTodoStatus(int id, String status);
    void deleteTodoById(int id);
}
package com.example.todo.service;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.HttpStatus;
 import org.springframework.jdbc.core.JdbcTemplate;
 import org.springframework.stereotype.Service;
 import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import com.example.todo.repository.TodoRepository;
import com.example.todo.model.Todo;
import java.util.*;
import com.example.todo.model.TodoRowMapper;
@Service
public class TodoH2Service implements TodoRepository{
    @Autowired
    private JdbcTemplate db;
@Override
public ArrayList<Todo>getAllTodos(){
List<Todo>todoList=db.query("select * from todolist",new TodoRowMapper());
 ArrayList<Todo>todos=new ArrayList<>(todoList);
return todos;

}
@Override
public Todo getTodoById(int todoId){
    try{
Todo todo=db.queryForObject("select * from todolist where id = ?", new TodoRowMapper(),todoId);
          return todo;
       }catch (Exception e){
throw new ResponseStatusException(HttpStatus.NOT_FOUND);
}
}
@Override
    public Todo createTodo(Todo todo) {
        db.update("insert into todolist (todo, status, priority) VALUES (?, ?, ?)",
                todo.getTodo(), todo.getStatus(), todo.getPriority());
        int newId = db.queryForObject("SELECT MAX(id) FROM TODOLIST", Integer.class);
        todo.setId(newId);
        return todo;
    }
@Override
    public Todo updateTodoStatus(int id, String status) {
        int affectedRows = db.update("UPDATE TODOLIST SET status = ? WHERE id = ?", status, id);
        if (affectedRows == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return getTodoById(id);
    }
@Override
public void deleteTodoById(int id){
    db.update("DELETE FROM todolist WHERE id=?",id);
}
}
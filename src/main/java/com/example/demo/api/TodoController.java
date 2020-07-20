package com.example.demo.api;

import java.util.List;


import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Todo;
import com.example.demo.service.TodoService;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class TodoController {
    public static Logger logger = LoggerFactory.getLogger(TodoController.class);

    @Autowired
    TodoService todoService;

    @RequestMapping(value = "/todo/", method = RequestMethod.GET)
    public ResponseEntity<List<Todo>> listAllContact(){
        List<Todo> listTodo= todoService.findAll();
        if(listTodo.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Todo>>(listTodo, HttpStatus.OK);
    }

    @RequestMapping(value = "/todo/{id}", method = RequestMethod.GET)
    public Todo findContact(@PathVariable("id") long id) {
        Todo todo= todoService.getOne(id);
        if(todo == null) {
            ResponseEntity.notFound().build();
        }
        return todo;
    }

    @RequestMapping(value = "/todo/", method = RequestMethod.POST)
    public Todo saveContact(@RequestBody Todo todo) {
        return todoService.save(todo);
    }

    @RequestMapping(value = "/todo/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Todo> updateContact(@PathVariable(value = "id") Long todoId,
                                              @RequestBody Todo todoForm) {
        Todo todo = todoService.getOne(todoId);
        if(todo == null) {
            return ResponseEntity.notFound().build();
        }
        todo.setTitle(todoForm.getTitle());
        todo.setDetail(todoForm.getDetail());

        Todo updatedTodo = todoService.save(todo);
        return ResponseEntity.ok(updatedTodo);
    }

    @RequestMapping(value = "/contact/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Todo> deleteContact(@PathVariable(value = "id") Long id) {
        Todo todo = todoService.getOne(id);
        if(todo == null) {
            return ResponseEntity.notFound().build();
        }

        todoService.delete(todo);
        return ResponseEntity.ok().build();
    }
}
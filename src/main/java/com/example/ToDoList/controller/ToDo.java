package com.example.ToDoList.controller;

import com.example.ToDoList.entity.ToDoEntity;
import com.example.ToDoList.exeption.*;
import com.example.ToDoList.module.ToDoModule;
import org.springframework.web.bind.annotation.*;
import com.example.ToDoList.services.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

@RestController
@RequestMapping("/api/todo")
public class ToDo {

    @Autowired
    private ToDoService toDoService;

    @PostMapping
    public ResponseEntity createToDo(@RequestBody ToDoEntity toDo) {
        try {
            ToDoEntity todo = toDoService.createAndGetToDo(toDo);
            return ResponseEntity.ok(todo);
        } catch (ToDoAlreadyExist e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Oops, something went wrong");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getToDoByID( @PathVariable String id) {
        try {
            return ResponseEntity.ok(toDoService.getToDoByID(Long.valueOf(id)));
        } catch (ToDoNotFound e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Oops, something went wrong");
        }
    }

    @GetMapping("title")
    public ResponseEntity getToDoByTitle(@RequestParam String title) {
        try {
            ToDoEntity toDo = toDoService.getToDoByTitle(title);
            return ResponseEntity.ok(toDo);
        } catch (ToDoNotFound e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Oops, something went wrong");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteToDo( @PathVariable String id) {
        try {
            System.out.println(id);
            toDoService.deleteToDo(Long.valueOf(id));
            return ResponseEntity.ok("ToDo deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Oops, something went wrong");
        }
    }


    @GetMapping
    public ResponseEntity getToDoList() {
        try {
            return ResponseEntity.ok(toDoService.getToDoList());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Oops, something went wrong");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity changeToDone(@PathVariable String id) {
        try {
            ToDoModule todo = toDoService.changeToDone(Long.valueOf(id));
            return ResponseEntity.ok(todo);
        } catch (ToDoNotFound e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Oops, something went wrong");
        }
    }

}

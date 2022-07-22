package com.example.ToDoList.exeption;

public class ToDoAlreadyExist extends Exception {
    public ToDoAlreadyExist(String message) {
        super(message);
    }
}

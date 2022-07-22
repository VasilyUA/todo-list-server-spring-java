package com.example.ToDoList.module;

import com.example.ToDoList.entity.ToDoEntity;

public class ToDoModule {
    private Boolean done;

    public static ToDoModule toModel(ToDoEntity toDo) {
        ToDoModule todo = new ToDoModule();
        todo.setDone(toDo.isDone());
        return todo;
    }

    public ToDoModule() {
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}

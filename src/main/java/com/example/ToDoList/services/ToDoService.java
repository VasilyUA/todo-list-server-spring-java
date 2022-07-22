package com.example.ToDoList.services;

import com.example.ToDoList.entity.ToDoEntity;
import com.example.ToDoList.exeption.*;
import com.example.ToDoList.module.ToDoModule;
import com.example.ToDoList.repository.ToDoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToDoService {
    @Autowired
    private ToDoRepo toDoRepo;

    public ToDoEntity createAndGetToDo(ToDoEntity toDo) throws ToDoAlreadyExist {
        if (toDoRepo.findByTitle(toDo.getTitle()) != null) {
            throw new ToDoAlreadyExist("ToDo with this title already exists");
        }
        toDoRepo.save(toDo);
        return toDoRepo.findByTitle(toDo.getTitle());
    }

    public ToDoEntity getToDoByID(Long id) throws ToDoNotFound {
        return toDoRepo.findById(id).orElseThrow(() -> new ToDoNotFound("ToDo not found with id: " + id));
    }

    public ToDoEntity getToDoByTitle(String title) throws ToDoNotFound {
        ToDoEntity toDo = toDoRepo.findByTitle(title);

        if (toDo == null) throw new ToDoNotFound("ToDo not found with title: " + title);

        return toDo;
    }

    public Iterable<ToDoEntity> getToDoList() {
        return toDoRepo.findAll();
    }

    public void deleteToDo(Long id) throws ToDoNotFound {
        ToDoEntity toDo = toDoRepo.findById(id).orElseThrow(() -> new ToDoNotFound("ToDo not found with id: " + id));
        toDoRepo.delete(toDo);
    }

    public ToDoModule changeToDone(Long id) throws ToDoNotFound {
        ToDoEntity toDo = toDoRepo.findById(id).orElseThrow(() -> new ToDoNotFound("ToDo not found with id: " + id));
        toDo.setDone(!toDo.isDone());
        toDoRepo.save(toDo);
        return ToDoModule.toModel(toDo);
    }
}

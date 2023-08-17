package com.tarefas.controllers;

import com.tarefas.dtos.TaskDTO;
import com.tarefas.entities.Task;
import com.tarefas.repositories.TaskRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository repository;

    @GetMapping
    public ResponseEntity<List<Task>> findAll(){
        var listar = repository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(listar);
    }

    @PostMapping
    public ResponseEntity<Task> post(@RequestBody TaskDTO taskDTO){
        var task = new Task();
        BeanUtils.copyProperties(taskDTO, task);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(task));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable Long id){
        Optional<Task> task = repository.findById(id);
        if (task.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(task);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@RequestBody TaskDTO taskDTO, @PathVariable Long id){
        //Task task = new Task(taskDTO);
        Optional<Task> task = repository.findById(id);
        if (task.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
        }else {
            var tasks = task.get();
            BeanUtils.copyProperties(taskDTO, tasks);
            return ResponseEntity.status(HttpStatus.OK).body(repository.save(tasks));
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        Optional<Task> task = repository.findById(id);
        if (task.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
        }else {
            repository.delete(task.get());
            return ResponseEntity.status(HttpStatus.OK).body("Task deleted successfully");
        }

    }

}

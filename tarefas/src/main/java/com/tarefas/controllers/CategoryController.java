package com.tarefas.controllers;

import com.tarefas.entities.Category;
import com.tarefas.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoryController {

    @Autowired
    private CategoryRepository repository;

    @GetMapping
    public ResponseEntity<List<Category>> findAll(){
        var listar = repository.findAll();
        return ResponseEntity.ok(listar);
    }
    @PostMapping
    public ResponseEntity<Category> post(@RequestBody Category category){
        var postar = repository.save(category);
        return new ResponseEntity<>(postar, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Category> atualizar(@RequestBody Category category){
        var atualizar = repository.save(category);
        return new ResponseEntity<>(atualizar, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

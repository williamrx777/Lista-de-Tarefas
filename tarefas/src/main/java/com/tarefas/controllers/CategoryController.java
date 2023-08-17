package com.tarefas.controllers;

import com.tarefas.dtos.CategoryDTO;
import com.tarefas.entities.Category;
import com.tarefas.repositories.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoryController {

    @Autowired
    private CategoryRepository repository;

    @GetMapping
    public ResponseEntity<List<Category>> findAll(){
        List<Category> categories = repository.findAll();
        return ResponseEntity.ok(categories);
    }
    @PostMapping
    public ResponseEntity<Category> post(@RequestBody CategoryDTO categoryDTO){
        var category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable Long id ){
        Optional<Category> category = repository.findById(id);
        if (category.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(category);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@RequestBody CategoryDTO categoryDTO,@PathVariable Long id){
        //Category category = new Category(categoryDTO);
        Optional<Category> category = repository.findById(id);
        if (category.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
        }
        var categories = category.get();
        BeanUtils.copyProperties(categoryDTO, categories);
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(categories));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        Optional<Category> category = repository.findById(id);
        if (category.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
        }
        repository.delete(category.get());
        return ResponseEntity.status(HttpStatus.OK).body("Category deleted successfully");
    }

}

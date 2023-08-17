package com.tarefas.dtos;

import com.tarefas.entities.Category;
import com.tarefas.entities.Status;


public record TaskDTO(String title, String description, Status status, Category categories) {



}

package com.boardcamp.api.controllers;

import com.boardcamp.api.controllers.dto.CategoriesDto;
import com.boardcamp.api.model.Categories;
import com.boardcamp.api.services.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriesController {

    @Autowired
    CategoriesService service;
    @GetMapping
    public ResponseEntity<List<CategoriesDto>> FindCategories(){
        List<CategoriesDto> categoriesDto = service.GetCategories();
        return ResponseEntity.ok().body(categoriesDto);
    }

    @PostMapping
    public ResponseEntity<Categories> PostCategories(@RequestBody Categories req){
        service.PostCategories(req);
        return ResponseEntity.ok().build();
    }
}

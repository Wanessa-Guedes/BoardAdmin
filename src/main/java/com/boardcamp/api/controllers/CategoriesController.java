package com.boardcamp.api.controllers;

import com.boardcamp.api.controllers.dto.CategoriesDto;
import com.boardcamp.api.middleware.ErrorHandler400;
import com.boardcamp.api.middleware.ErrorHandler409;
import com.boardcamp.api.model.Categories;
import com.boardcamp.api.services.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriesController {

    @Autowired
    CategoriesService service;
//    @GetMapping
//    public ResponseEntity<List<CategoriesDto>> FindCategories(){
//        List<CategoriesDto> categoriesDto = service.GetCategories();
//        return ResponseEntity.ok().body(categoriesDto);
//    }

    @GetMapping
    public ResponseEntity<List<CategoriesDto>> FindCategories(@RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
                                                              @RequestParam(value = "limit", required = false, defaultValue = "5") int limit){
        List<CategoriesDto> categoriesDto = service.GetCategories(offset, limit);
        return ResponseEntity.ok().body(categoriesDto);
    }

    @PostMapping
    public ResponseEntity<Categories> PostCategories(@Valid @RequestBody Categories req) throws ErrorHandler400, ErrorHandler409 {
        service.PostCategories(req);
        return ResponseEntity.ok().build();
    }
}

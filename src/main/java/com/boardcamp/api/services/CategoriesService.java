package com.boardcamp.api.services;

import com.boardcamp.api.controllers.dto.CategoriesDto;
import com.boardcamp.api.middleware.ErrorHandler400;
import com.boardcamp.api.middleware.ErrorHandler409;
import com.boardcamp.api.model.Categories;
import com.boardcamp.api.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriesService {

    @Autowired
    private CategoriesRepository repository;

//    public List<CategoriesDto> GetCategories(){
//        List<Categories> categories = repository.findAll();
//        List<CategoriesDto> categoriesDto = new ArrayList<>();
//        categories.forEach(categorie -> categoriesDto.add(new CategoriesDto(categorie)));
//        return categoriesDto;
//    }

    public List<CategoriesDto> GetCategories(int offset, int limit){
        Page<Categories> offCategories = repository.findAll(PageRequest.of(offset, limit));
        //List<Categories> categories = repository.findAll();
        List<CategoriesDto> categoriesDto = new ArrayList<>();
        offCategories.forEach(categorie -> categoriesDto.add(new CategoriesDto(categorie)));
        return categoriesDto;
    }

    public Categories PostCategories(Categories data) throws ErrorHandler400, ErrorHandler409 {
        Categories category = repository.findByName(data.getName());
        if(data.getName() == null || data.getName().length() == 0){
            throw new ErrorHandler400("400","Nome da Categoria deve ser inserido");
        } else if(category != null){
            throw new ErrorHandler409("409", "Categoria com nome já cadastrado");
        }
        return repository.save(data);
    }
}

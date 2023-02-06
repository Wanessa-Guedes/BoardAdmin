package com.boardcamp.api.services;

import com.boardcamp.api.controllers.dto.CategoriesDto;
import com.boardcamp.api.middleware.ErrorHandler;
import com.boardcamp.api.model.Categories;
import com.boardcamp.api.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriesService {

    @Autowired
    private CategoriesRepository repository;

    public List<CategoriesDto> GetCategories(){
        List<Categories> categories = repository.findAll();
        List<CategoriesDto> categoriesDto = new ArrayList<>();
        categories.forEach(categorie -> categoriesDto.add(new CategoriesDto(categorie)));
        return categoriesDto;
    }

    public Categories PostCategories(Categories data) throws ErrorHandler {
        if(data.getName() == null || data.getName().length() == 0){
            throw new ErrorHandler("400","Nome da Categoria deve ser inserido");
        }
        return repository.save(data);
    }
}

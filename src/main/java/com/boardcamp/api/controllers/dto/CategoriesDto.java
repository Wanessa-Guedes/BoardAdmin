package com.boardcamp.api.controllers.dto;

import com.boardcamp.api.model.Categories;

public class CategoriesDto {
    private long id;
    private String name;

    public CategoriesDto() {
    }

    public CategoriesDto(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoriesDto(Categories categorie) {
        this.id = categorie.getId();
        this.name = categorie.getName();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

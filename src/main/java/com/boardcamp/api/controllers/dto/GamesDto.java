package com.boardcamp.api.controllers.dto;

import com.boardcamp.api.model.Games;

public class GamesDto {

    private long id;
    private String name;

    private String image;
    private Integer stockTotal;
    private Integer category_set_id;
    private Float pricePerDay;
    private String categoryName;

    public GamesDto(long id, String name, String image, Integer stockTotal, Integer category_set_id, Float pricePerDay, String categoryName) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.stockTotal = stockTotal;
        this.category_set_id = category_set_id;
        this.pricePerDay = pricePerDay;
        this.categoryName = categoryName;
    }

    public GamesDto() {
    }

    public GamesDto(Games game) {
        this.id = game.getId();
        this.name = game.getName();
        this.image = game.getImage();
        this.stockTotal = game.getStockTotal();
        this.category_set_id = game.getCategory_set_id();
        this.pricePerDay = game.getPricePerDay();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStockTotal() {
        return stockTotal;
    }

    public void setStockTotal(Integer stockTotal) {
        this.stockTotal = stockTotal;
    }

    public Integer getCategory_set_id() {
        return category_set_id;
    }

    public void setCategory_set_id(Integer category_set_id) {
        this.category_set_id = category_set_id;
    }

    public Float getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Float pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}

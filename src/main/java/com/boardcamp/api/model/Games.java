package com.boardcamp.api.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="games")
public class Games {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Nome não pode estar em branco.")
    private String name;

    private String image;

    @NotNull(message = "StockTotal precisa ter valor." )
    private Integer stockTotal;

    @NotNull(message = "PricePerDay precisa ter valor." )
    private Float pricePerDay;

    @NotNull(message = "Category precisa ter valor." )
    private Integer category_set_id;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Categories categories;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getStockTotal() {
        return stockTotal;
    }

    public void setStockTotal(Integer stockTotal) {
        this.stockTotal = stockTotal;
    }

    public Float getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Float pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public Integer getCategory_set_id() {
        return category_set_id;
    }

    public void setCategory_set_id(Integer category_set_id) {
        this.category_set_id = category_set_id;
    }


}

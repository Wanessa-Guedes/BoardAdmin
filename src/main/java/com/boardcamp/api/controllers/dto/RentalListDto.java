package com.boardcamp.api.controllers.dto;

import com.boardcamp.api.model.Customers;
import com.boardcamp.api.model.Games;
import com.boardcamp.api.model.Rental;

import javax.persistence.*;
import java.time.LocalDate;

public class RentalListDto {


    private long id;
    private LocalDate rentDate;
    private Integer daysRented;
    private LocalDate returnDate;
    private Float originalPrice;
    private Float delayFee;

    private Customers customer;

    private GamesDto gamesDto;

    public RentalListDto() {
    }

    public RentalListDto(long id, LocalDate rentDate, Integer daysRented, LocalDate returnDate, Float originalPrice, Float delayFee, Customers customer) {
        this.id = id;
        this.rentDate = rentDate;
        this.daysRented = daysRented;
        this.returnDate = returnDate;
        this.originalPrice = originalPrice;
        this.delayFee = delayFee;
        this.customer = customer;
    }

    public RentalListDto(Rental rental) {
        this.id = rental.getId();
        this.rentDate = rental.getRentDate();
        this.daysRented = rental.getDaysRented();
        this.returnDate = rental.getReturnDate();
        this.originalPrice = rental.getOriginalPrice();
        this.delayFee = rental.getDelayFee();
        this.customer = rental.getCustomer();
        this.gamesDto = new GamesDto(rental.getGame());
    }

    public long getId() {
        return id;
    }

    public LocalDate getRentDate() {
        return rentDate;
    }

    public void setRentDate(LocalDate rentDate) {
        this.rentDate = rentDate;
    }

    public Integer getDaysRented() {
        return daysRented;
    }

    public void setDaysRented(Integer daysRented) {
        this.daysRented = daysRented;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Float getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Float originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Float getDelayFee() {
        return delayFee;
    }

    public void setDelayFee(Float delayFee) {
        this.delayFee = delayFee;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public GamesDto getGamesDto() {
        return gamesDto;
    }

    public void setGamesDto(GamesDto gamesDto) {
        this.gamesDto = gamesDto;
    }

}

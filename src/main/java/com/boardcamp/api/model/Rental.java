package com.boardcamp.api.model;

import javax.persistence.*;

@Entity
@Table(name="rental")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String rentDate;
    private String daysRented;
    private String returnDate;
    private Float originalPrice;
    private Float delayFee;

    // costumerId // gameId
    @ManyToOne
    @JoinColumn(name="customer_id", referencedColumnName = "id")
    private Customers customer;

    @ManyToOne
    @JoinColumn(name="game_id", referencedColumnName = "id")
    private Games game;

    public String getRentDate() {
        return rentDate;
    }

    public void setRentDate(String rentDate) {
        this.rentDate = rentDate;
    }

    public String getDaysRented() {
        return daysRented;
    }

    public void setDaysRented(String daysRented) {
        this.daysRented = daysRented;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
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

    public Games getGame() {
        return game;
    }

    public void setGame(Games game) {
        this.game = game;
    }
}

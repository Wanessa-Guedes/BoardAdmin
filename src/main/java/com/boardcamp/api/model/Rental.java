package com.boardcamp.api.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="rental")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private LocalDate rentDate;
    @Column(nullable = false)
    private Integer daysRented;
    private LocalDate returnDate;
    @Column(nullable = false)
    private Float originalPrice;
    private Float delayFee;

    // costumerId // gameId
    @ManyToOne
    @JoinColumn(name="customer_id", referencedColumnName = "id")
    private Customers customer;

    @ManyToOne
    @JoinColumn(name="game_id", referencedColumnName = "id")
    private Games game;

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

    public Games getGame() {
        return game;
    }

    public void setGame(Games game) {
        this.game = game;
    }
}

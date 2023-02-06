package com.boardcamp.api.controllers.dto;

import java.time.LocalDate;

public class RentalDto {

    private Long customerId;

    private Long gameId;

    private Integer daysRented;

    public RentalDto() {
    }

    public RentalDto(Long customerId, Long gameId, Integer daysRented) {
        this.customerId = customerId;
        this.gameId = gameId;
        this.daysRented = daysRented;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Integer getDaysRented() {
        return daysRented;
    }

    public void setDaysRented(Integer daysRented) {
        this.daysRented = daysRented;
    }
}

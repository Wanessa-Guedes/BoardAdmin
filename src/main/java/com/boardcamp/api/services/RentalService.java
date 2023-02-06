package com.boardcamp.api.services;

import com.boardcamp.api.controllers.dto.RentalDto;
import com.boardcamp.api.controllers.dto.RentalListDto;
import com.boardcamp.api.model.Customers;
import com.boardcamp.api.model.Games;
import com.boardcamp.api.model.Rental;
import com.boardcamp.api.repository.CategoriesRepository;
import com.boardcamp.api.repository.CustomersRepository;
import com.boardcamp.api.repository.GamesRepository;
import com.boardcamp.api.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class RentalService {

    @Autowired
    RentalRepository rentalRepository;

    @Autowired
    CustomersRepository customersRepository;

    @Autowired
    GamesRepository gamesRepository;

    @Autowired
    CategoriesRepository categoriesRepository;

    public List<RentalListDto> GetRentalsInfos(){
       List<Rental> rentals = rentalRepository.findAll();
       List<RentalListDto> rentalListDtos = new ArrayList<>();
       rentals.forEach(rental -> rentalListDtos.add(new RentalListDto(rental)));
        rentalListDtos.forEach(rentalDto -> rentalDto.getGamesDto()
                .setCategoryName(String.valueOf(categoriesRepository
                        .findById(Long.valueOf(rentalDto.getGamesDto()
                                .getCategory_set_id())).get().getName())));
       return rentalListDtos;
    }

    public Rental PostRentalInfo(RentalDto data){
        Games game = gamesRepository.findById(data.getGameId()).get();
        Customers customer = customersRepository.findById(data.getCustomerId()).get();
        Float price = Integer.valueOf(String.valueOf(data.getDaysRented())) * game.getPricePerDay();
        Rental rental = new Rental();
        rental.setDaysRented(data.getDaysRented());
        rental.setCustomer(customer);
        rental.setGame(game);
        rental.setDelayFee(null);
        rental.setRentDate(LocalDate.now());
        rental.setOriginalPrice(price);
        rental.setReturnDate(null);
        return rentalRepository.save(rental);
    }

    public Rental PostReturn(long id){
        Rental rental = rentalRepository.findById(id);
        LocalDate returnDate = LocalDate.now();
        //LocalDate returnDate = LocalDate.now().plusDays(10);
        if(rental != null){
            Duration dateDiff = Duration.between(rental.getRentDate().atStartOfDay(), returnDate.atStartOfDay());
            Float fee = 0.0F;
            if(dateDiff.toDays() > (rental.getDaysRented()/(1000*60*60*24))){
                fee = (dateDiff.toDays() - rental.getDaysRented().intValue())*rental.getGame().getPricePerDay();
                rental.setDelayFee(fee);
            } else {
                rental.setDelayFee((float) 0);
            }
            rental.setReturnDate(LocalDate.now());
            return rentalRepository.save(rental);

        }
        return null;
    }

    public void DeleteRental(long id){
        Rental rental = rentalRepository.findById(id);
        if(rental != null){
            rentalRepository.delete(rental);
        }
    }
}

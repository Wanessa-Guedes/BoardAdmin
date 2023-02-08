package com.boardcamp.api.services;

import com.boardcamp.api.controllers.dto.RentalDto;
import com.boardcamp.api.controllers.dto.RentalListDto;
import com.boardcamp.api.middleware.ErrorHandler400;
import com.boardcamp.api.middleware.ErrorHandler404;
import com.boardcamp.api.model.Customers;
import com.boardcamp.api.model.Games;
import com.boardcamp.api.model.QRental;
import com.boardcamp.api.model.Rental;
import com.boardcamp.api.repository.CategoriesRepository;
import com.boardcamp.api.repository.CustomersRepository;
import com.boardcamp.api.repository.GamesRepository;
import com.boardcamp.api.repository.RentalRepository;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

    public List<RentalListDto> GetRentalsInfos(Long customerId, Long gameId, int offset, int limit){
        if(customerId == null && gameId == null){
            List<Rental> rentals = rentalRepository.findAll(PageRequest.of(offset, limit)).getContent();
            List<RentalListDto> rentalListDtos = new ArrayList<>();
            rentals.forEach(rental -> rentalListDtos.add(new RentalListDto(rental)));
            rentalListDtos.forEach(rentalDto -> rentalDto.getGamesDto()
                    .setCategoryName(String.valueOf(categoriesRepository
                            .findById(Long.valueOf(rentalDto.getGamesDto()
                                    .getCategory_set_id())).get().getName())));
            return rentalListDtos;
        }
//        QRental rentalDsl = QRental.rental;
//        BooleanBuilder builder = new BooleanBuilder();
        if(customerId != null){
//            builder.and(rentalDsl.customer.id.in(customerId));
            //List<Rental> rentals = (List<Rental>) rentalRepository.findAll(builder);
            List<Rental> rentals = rentalRepository.findAllByCustomerId(customerId, PageRequest.of(offset, limit)).getContent();
            List<RentalListDto> rentalListDtos = new ArrayList<>();
            rentals.forEach(rental -> rentalListDtos.add(new RentalListDto(rental)));
            rentalListDtos.forEach(rentalDto -> rentalDto.getGamesDto()
                    .setCategoryName(String.valueOf(categoriesRepository
                            .findById(Long.valueOf(rentalDto.getGamesDto()
                                    .getCategory_set_id())).get().getName())));
            return rentalListDtos;
        } else {
//            builder.and(rentalDsl.game.id.in(gameId));

//            List<Rental> rentals = (List<Rental>) rentalRepository.findAll(builder);
            List<Rental> rentals = rentalRepository.findAllByGameId(gameId, PageRequest.of(offset, limit)).getContent();
            List<RentalListDto> rentalListDtos = new ArrayList<>();
            rentals.forEach(rental -> rentalListDtos.add(new RentalListDto(rental)));
            rentalListDtos.forEach(rentalDto -> rentalDto.getGamesDto()
                    .setCategoryName(String.valueOf(categoriesRepository
                            .findById(Long.valueOf(rentalDto.getGamesDto()
                                    .getCategory_set_id())).get().getName())));
            return rentalListDtos;
        }


    }

    public Rental PostRentalInfo(RentalDto data) throws ErrorHandler400 {
        Customers customer = customersRepository.findById(data.getCustomerId()).orElse(null);
        if(customer == null){
            throw new ErrorHandler400("400", "Não existe usuário cadastrado com esse Id");
        }
        Games game = gamesRepository.findById(data.getGameId()).orElse(null);
        if(game == null){
            throw new ErrorHandler400("400", "Não existe jogo cadastrado com esse Id");
        }
        if(data.getDaysRented() <= 0){
            throw new ErrorHandler400("400", "DaysRented precisa ser maior do que zero");
        }
        if(game.getStockTotal() <= 0){
            throw new ErrorHandler400("400", "Não existem jogos desse tipo disponíveis no estoque");
        }
        Float price = Integer.valueOf(String.valueOf(data.getDaysRented())) * game.getPricePerDay();
        Rental rental = new Rental();
        rental.setDaysRented(data.getDaysRented());
        rental.setCustomer(customer);
        rental.setGame(game);
        rental.setDelayFee(null);
        rental.setRentDate(LocalDate.now());
        rental.setOriginalPrice(price);
        rental.setReturnDate(null);
        game.setStockTotal(game.getStockTotal() - 1);
        return rentalRepository.save(rental);
    }

    public Rental PostReturn(long id) throws ErrorHandler404, ErrorHandler400 {
        Rental rental = rentalRepository.findById(id);
        if(rental == null){
            throw new ErrorHandler404("404", "Não existem aluguéis com esse id");
        }
        if(rental.getReturnDate() != null){
            throw new  ErrorHandler400("400", "Esse aluguel já foi finalizado");
        }
        Games games = gamesRepository.findById(rental.getGame().getId());
        games.setStockTotal(games.getStockTotal() + 1);
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

    public void DeleteRental(long id) throws ErrorHandler404, ErrorHandler400 {
        Rental rental = rentalRepository.findById(id);
        if(rental == null){
            throw new ErrorHandler404("404", "Rental não existe.");
        }
        if(rental.getReturnDate() == null){
            throw new ErrorHandler400("400", "Rental não pode ser deletado. Status em aberto.");
        }
        rentalRepository.delete(rental);
    }
}

package com.boardcamp.api.services;

import com.boardcamp.api.controllers.dto.GamesDto;
import com.boardcamp.api.middleware.ErrorHandler400;
import com.boardcamp.api.middleware.ErrorHandler409;
import com.boardcamp.api.model.Categories;
import com.boardcamp.api.model.Games;
import com.boardcamp.api.model.QGames;
import com.boardcamp.api.repository.CategoriesRepository;
import com.boardcamp.api.repository.GamesRepository;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GamesService {

    @Autowired
    GamesRepository gamesRepository;

    @Autowired
    CategoriesRepository categoriesRepository;

    public List<GamesDto> GetGames(String name, int offset, int limit){
    if(name == null) {
        List<Games> games = gamesRepository.findAll(PageRequest.of(offset, limit)).getContent();
        List<GamesDto> gamesDto = new ArrayList<>();
        games.forEach(game -> gamesDto.add(new GamesDto(game)));
        gamesDto.forEach(gameDto -> gameDto
                .setCategoryName(String.valueOf(categoriesRepository
                        .findById(Long.valueOf(gameDto
                                .getCategory_set_id())).get().getName())));
        return gamesDto;
    }
//        QGames gamesDsl = QGames.games;
//        BooleanBuilder builder = new BooleanBuilder();
//        builder.and(gamesDsl.name.containsIgnoreCase(name));
//        List<Games> games = (List<Games>) gamesRepository.findAll(builder);
        List<Games> games = gamesRepository.findAllByNameIgnoreCaseStartingWith(name, PageRequest.of(offset, limit)).getContent();
        List<GamesDto> gamesDto = new ArrayList<>();
        games.forEach(game -> gamesDto.add(new GamesDto(game)));
        gamesDto.forEach(gameDto -> gameDto
                .setCategoryName(String.valueOf(categoriesRepository
                        .findById(Long.valueOf(gameDto
                                .getCategory_set_id())).get().getName())));
        return gamesDto;

    }

    public Games PostGames(Games data) throws ErrorHandler400, ErrorHandler409 {
        Games game = gamesRepository.findByName(data.getName());
        if(game != null){
            throw new ErrorHandler409("409", "Já possui um jogo cadastrado com esse nome!");
        }
        if(data.getName() == null || data.getName().length() == 0 ){
            throw new ErrorHandler400("400", "Nome do jogo não pode estar vazio");
        }
        if(data.getStockTotal() <= 0 || data.getPricePerDay() <= 0){
            throw new ErrorHandler400("400", "stockTotal e pricePerDay precisam ser valores positivos");
        }
        Categories category = categoriesRepository.findById(data.getCategory_set_id());
        if(category == null){
            throw new ErrorHandler400("400", "Essa categoria não foi cadastrada ainda");
        }
        return gamesRepository.save(data);
    }
}

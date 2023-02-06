package com.boardcamp.api.services;

import com.boardcamp.api.controllers.dto.GamesDto;
import com.boardcamp.api.model.Categories;
import com.boardcamp.api.model.Games;
import com.boardcamp.api.repository.CategoriesRepository;
import com.boardcamp.api.repository.GamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GamesService {

    @Autowired
    GamesRepository gamesRepository;

    @Autowired
    CategoriesRepository categoriesRepository;

    public List<GamesDto> GetGames(){
        List<Games> games = gamesRepository.findAll();
        List<GamesDto> gamesDto = new ArrayList<>();
        games.forEach(game -> gamesDto.add(new GamesDto(game)));
        gamesDto.forEach(gameDto -> gameDto
                .setCategoryName(String.valueOf(categoriesRepository
                        .findById(Long.valueOf(gameDto
                                .getCategory_set_id())).get().getName())));
        return gamesDto;
    }

    public Games PostGames(Games data){
        Categories category = categoriesRepository.findById(data.getCategory_set_id());
        return gamesRepository.save(data);
    }
}

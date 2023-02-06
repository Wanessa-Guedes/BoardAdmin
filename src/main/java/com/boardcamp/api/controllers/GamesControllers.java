package com.boardcamp.api.controllers;

import com.boardcamp.api.controllers.dto.GamesDto;
import com.boardcamp.api.model.Games;
import com.boardcamp.api.services.CategoriesService;
import com.boardcamp.api.services.GamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/games")
public class GamesControllers {

    @Autowired
    GamesService gamesService;

    @GetMapping
    public ResponseEntity<List<GamesDto>> GetAllGames(){
        List<GamesDto> games = gamesService.GetGames();
        return ResponseEntity.ok().body(games);
    }

    @PostMapping
    public ResponseEntity<Objects> PostGame(@Valid @RequestBody Games req){
         gamesService.PostGames(req);
         return ResponseEntity.ok().build();
    }
}

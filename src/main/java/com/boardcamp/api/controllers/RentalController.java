package com.boardcamp.api.controllers;

import com.boardcamp.api.controllers.dto.RentalDto;
import com.boardcamp.api.controllers.dto.RentalListDto;
import com.boardcamp.api.model.Rental;
import com.boardcamp.api.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    @Autowired
    RentalService rentalService;

    @GetMapping
    public ResponseEntity<List<RentalListDto>> GetRentals(){
        List<RentalListDto> rentals = rentalService.GetRentalsInfos();
        return ResponseEntity.ok().body(rentals);
    }

    @PostMapping
    public ResponseEntity<Object> PostRental(@RequestBody RentalDto req){
        rentalService.PostRentalInfo(req);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/return")
    public ResponseEntity<Object> PostReturnRental(@PathVariable long id){
        rentalService.PostReturn(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> DeleteRental(@PathVariable long id){
        rentalService.DeleteRental(id);
        return ResponseEntity.ok().build();
    }
}

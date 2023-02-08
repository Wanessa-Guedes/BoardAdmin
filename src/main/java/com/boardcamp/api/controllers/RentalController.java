package com.boardcamp.api.controllers;

import com.boardcamp.api.controllers.dto.RentalDto;
import com.boardcamp.api.controllers.dto.RentalListDto;
import com.boardcamp.api.middleware.ErrorHandler400;
import com.boardcamp.api.middleware.ErrorHandler404;
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

//    @GetMapping
//    public ResponseEntity<List<RentalListDto>> GetRentals(){
//        List<RentalListDto> rentals = rentalService.GetRentalsInfos();
//        return ResponseEntity.ok().body(rentals);
//    }

    @GetMapping
    public ResponseEntity<List<RentalListDto>> GetRentals(@RequestParam(value="customerId", required=false) Long customerId,
                                                          @RequestParam(value="gameId", required=false) Long gameId){
        List<RentalListDto> rentals = rentalService.GetRentalsInfos(customerId, gameId);
        return ResponseEntity.ok().body(rentals);
    }

    @PostMapping
    public ResponseEntity<Object> PostRental(@RequestBody RentalDto req) throws ErrorHandler400 {
        rentalService.PostRentalInfo(req);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/return")
    public ResponseEntity<Object> PostReturnRental(@PathVariable long id) throws ErrorHandler404, ErrorHandler400 {
        rentalService.PostReturn(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> DeleteRental(@PathVariable long id) throws ErrorHandler404, ErrorHandler400 {
        rentalService.DeleteRental(id);
        return ResponseEntity.ok().build();
    }
}

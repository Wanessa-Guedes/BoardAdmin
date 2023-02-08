package com.boardcamp.api.controllers;

import com.boardcamp.api.middleware.ErrorHandler400;
import com.boardcamp.api.middleware.ErrorHandler404;
import com.boardcamp.api.middleware.ErrorHandler409;
import com.boardcamp.api.model.Customers;
import com.boardcamp.api.repository.CustomersRepository;
import com.boardcamp.api.services.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/customers")
public class CustomersController {

    @Autowired
    CustomersService customersService;

//    @GetMapping
//    public ResponseEntity<List<Customers>> GetAllCustomers(){
//        List<Customers> customers = customersService.GetCustomers();
//        return ResponseEntity.ok().body(customers);
//    }

    @GetMapping
    public ResponseEntity<List<Customers>> GetAllCustomers(@RequestParam(value = "cpf", required = false) String cpf,
                                                           @RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
                                                           @RequestParam(value = "limit", required = false, defaultValue = "5") int limit){
        List<Customers> customers = customersService.GetCustomers(cpf, offset, limit);
        return ResponseEntity.ok().body(customers);
    }

    @PostMapping
    public ResponseEntity<Object> PostCustomer(@Valid @RequestBody Customers req) throws ErrorHandler409 {
        customersService.PostCustomers(req);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customers> GetById(@PathVariable(value="id") long id) throws ErrorHandler404 {
        Customers customer = customersService.FindById(id);
        return ResponseEntity.ok().body(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> UpdateCustomer(@Valid @PathVariable(value="id") long id,
                                                    @RequestBody Customers req) throws ErrorHandler404, ErrorHandler409, ErrorHandler400 {
        if(req.getCpf().length() != 11 || req.getPhone().length() < 10
                || req.getPhone().length() > 11 || req.getName() == null
                || req.getName().length() == 0){
            throw new ErrorHandler400("400", "Dados Inválidos");
        }
        Customers customers = customersService.FindById(id);
        if(customers != null){
            customers.setName(req.getName());
            customers.setPhone(req.getPhone());
            customers.setCpf(req.getCpf());
            customers.setBirthday(req.getBirthday());
            customersService.PutCustomer(customers);
            return ResponseEntity.ok().build();
        }

        return null;
    }
}

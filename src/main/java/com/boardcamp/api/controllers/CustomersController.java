package com.boardcamp.api.controllers;

import com.boardcamp.api.model.Customers;
import com.boardcamp.api.repository.CustomersRepository;
import com.boardcamp.api.services.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/customers")
public class CustomersController {

    @Autowired
    CustomersService customersService;

    @GetMapping
    public ResponseEntity<List<Customers>> GetAllCustomers(){
        List<Customers> customers = customersService.GetCustomers();
        return ResponseEntity.ok().body(customers);
    }

    @PostMapping
    public ResponseEntity<Object> PostCustomer(@RequestBody Customers req){
        customersService.PostCustomers(req);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customers> GetById(@PathVariable(value="id") long id){
        Customers customer = customersService.FindById(id);
        return ResponseEntity.ok().body(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> UpdateCustomer(@PathVariable(value="id") long id,
                                                    @RequestBody Customers req){
        Customers customers = customersService.FindById(id);
        if(customers != null){
            customers.setName(req.getName());
            customers.setPhone(req.getPhone());
            customers.setCpf(req.getCpf());
            customers.setBirthday(req.getBirthday());
            customersService.PutCustomer(customers);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }
}
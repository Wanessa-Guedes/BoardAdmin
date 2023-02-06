package com.boardcamp.api.services;

import com.boardcamp.api.model.Customers;
import com.boardcamp.api.repository.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomersService {

    @Autowired
    CustomersRepository customersRepository;

    public List<Customers> GetCustomers(){
        return customersRepository.findAll();
    }

    public Customers PostCustomers(Customers customer){
        return customersRepository.save(customer);
    }

    public Customers FindById(Long data){
        return customersRepository.findById(data).get();
    }

    public Customers PutCustomer(Customers customer){
        return customersRepository.save(customer);
    }
}

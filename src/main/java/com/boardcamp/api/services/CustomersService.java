package com.boardcamp.api.services;

import com.boardcamp.api.model.Customers;
import com.boardcamp.api.model.QCustomers;
import com.boardcamp.api.repository.CustomersRepository;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomersService {

    @Autowired
    CustomersRepository customersRepository;

    public List<Customers> GetCustomers(String cpf){
        if(cpf == null){
            return customersRepository.findAll();
        }
        QCustomers customersDsl = QCustomers.customers;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(customersDsl.cpf.contains(cpf));
        List<Customers> customers = (List<Customers>) customersRepository.findAll(builder);
        return customers;
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

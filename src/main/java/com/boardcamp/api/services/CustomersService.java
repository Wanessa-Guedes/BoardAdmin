package com.boardcamp.api.services;

import com.boardcamp.api.middleware.ErrorHandler404;
import com.boardcamp.api.middleware.ErrorHandler409;
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

    public Customers PostCustomers(Customers customer) throws ErrorHandler409 {
        Customers customerData = customersRepository.findByCpf(customer.getCpf());

        if(customerData == null){
            return customersRepository.save(customer);
        }
        throw new ErrorHandler409("409", "Usuário já cadastrado!");

    }

    public Customers FindById(Long data) throws ErrorHandler404 {

        Customers customer = customersRepository.findById(data).orElse(null);
        if(customer == null){
            throw new ErrorHandler404("404", "Nenhum usuário cadastrado com esse id!");
        }
        return customer;
    }

    public Customers PutCustomer(Customers customer) throws ErrorHandler409 {

        Customers customerData = customersRepository.findByCpf(customer.getCpf());

        if(customerData == null || customerData.getId() == customer.getId()){
            return customersRepository.save(customer);
        }
        if(customerData.getId() != customer.getId()){
            throw new ErrorHandler409("409", "Outro usuário já cadastrado com o mesmo CPF!");
        }
        return null;
    }
}

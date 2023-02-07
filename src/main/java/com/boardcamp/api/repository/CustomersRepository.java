package com.boardcamp.api.repository;

import com.boardcamp.api.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepository extends JpaRepository<Customers, Long>, QuerydslPredicateExecutor<Customers> {

    Customers findById(long id);
    Customers findByCpf(String cpf);
}

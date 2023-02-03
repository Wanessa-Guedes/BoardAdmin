package com.boardcamp.api.repository;

import com.boardcamp.api.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepository extends JpaRepository<Customers, Long> {

    Customers findById(long id);
}

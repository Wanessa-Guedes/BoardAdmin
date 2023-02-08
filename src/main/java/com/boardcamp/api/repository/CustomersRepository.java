package com.boardcamp.api.repository;

import com.boardcamp.api.model.Customers;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.net.ContentHandler;
import java.util.List;

@Repository
public interface CustomersRepository extends JpaRepository<Customers, Long>,
        QuerydslPredicateExecutor<Customers>,
        PagingAndSortingRepository<Customers, Long> {

    Customers findById(long id);
    Customers findByCpf(String cpf);

    Page<Customers> findAllByCpfStartingWith(String cpf, Pageable pageable);


}

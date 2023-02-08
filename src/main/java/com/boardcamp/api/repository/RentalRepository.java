package com.boardcamp.api.repository;

import com.boardcamp.api.model.Rental;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long>, QuerydslPredicateExecutor<Rental> {
    Rental findById(long id);

    Page<Rental> findAllByGameId(long gameId, Pageable pageable);

    Page<Rental> findAllByCustomerId(Long customerId, Pageable pageable);
}

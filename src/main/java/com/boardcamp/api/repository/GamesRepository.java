package com.boardcamp.api.repository;

import com.boardcamp.api.model.Games;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GamesRepository extends JpaRepository<Games, Long>, QuerydslPredicateExecutor<Games> {

    Games findById(long id);

    Games findByName(String name);
}

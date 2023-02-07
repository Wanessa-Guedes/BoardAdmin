package com.boardcamp.api.repository;

import com.boardcamp.api.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Long>, QuerydslPredicateExecutor<Categories> {
    Categories findById(long id);

    Categories findByName(String name);
}

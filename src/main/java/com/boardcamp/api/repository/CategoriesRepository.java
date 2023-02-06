package com.boardcamp.api.repository;

import com.boardcamp.api.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Long> {
    Categories findById(long id);
}

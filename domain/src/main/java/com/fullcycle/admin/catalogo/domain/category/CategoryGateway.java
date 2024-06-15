package com.fullcycle.admin.catalogo.domain.category;

import com.fullcycle.admin.catalogo.domain.category.pagination.Pagination;

import java.util.List;
import java.util.Optional;

public interface CategoryGateway {

    Category create(Category aCategory);

    void deleteById(CategoryID anId);

    Optional<Category> findByID(CategoryID anId);

    Optional<Category> findById(CategoryID categoryId);

    Category update(Category aCategory);

    Pagination <Category> findAll(CategorySearchQuery aQuery);

    List <Object> existsByIds(Iterable <CategoryID> categoryIDs);
}


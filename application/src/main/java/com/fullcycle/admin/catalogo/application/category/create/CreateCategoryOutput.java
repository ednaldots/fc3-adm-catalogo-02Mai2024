package com.fullcycle.admin.catalogo.application.category.create;



import com.fullcycle.admin.catalogo.domain.category.Category;

public record CreateCategoryOutput(Object id, String name, String description) {

    public static CreateCategoryOutput from(Category aCategory) {
        if (aCategory == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }

        return new CreateCategoryOutput(aCategory.getId(), aCategory.getName(), aCategory.getDescription());
    }
}

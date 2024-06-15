package com.fullcycle.admin.infrastructure.utils;

import org.springframework.data.jpa.domain.Specification;

public final class SpecificationUtils {
    private SpecificationUtils() {
    }

    public static <T> Specification<T> like(final String attribute, final String term) {
        return (root, query, builder) -> builder.like(builder.lower(root.get(attribute)), "%" + term.toLowerCase() + "%");
    }
}
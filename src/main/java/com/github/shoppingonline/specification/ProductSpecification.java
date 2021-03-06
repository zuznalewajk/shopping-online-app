package com.github.shoppingonline.specification;

import com.github.shoppingonline.model.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.Set;

public final class ProductSpecification {

    public static Specification<Product> createProductSpecifications(ProductSearchCriteria criteria) {
        return priceBetween(criteria.getMinPrice(), criteria.getMaxPrice())
                .and(colorsIn(criteria.getColors()))
                .and(nameLike(criteria.getName()))
                .and(starRatingGreaterOrEqual(criteria.getMinStarRating()));
    }

    public static Specification<Product> colorsIn(Set<String> colors) {
        if (colors.isEmpty())
            return (root, query, builder) -> builder.conjunction();

        return (root, query, builder) ->
            root.<String> get("color").in(colors);

    }

    public static Specification<Product> priceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        return (root, query, builder) -> builder.between(root.<BigDecimal>get("price"), minPrice, maxPrice);
    }

    public static Specification<Product> nameLike(String name) {
        return (root, query, builder) -> builder.like(root.<String> get("name"), name);
    }

    public static Specification<Product> starRatingGreaterOrEqual(Double starRating) {

        return (root, query, builder) ->
                builder.greaterThanOrEqualTo(root.<Double> get("starRating"), starRating);
    }

}


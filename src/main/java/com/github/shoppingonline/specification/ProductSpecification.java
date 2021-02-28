package com.github.shoppingonline.specification;

import com.github.shoppingonline.model.Product;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

public final class ProductSpecification {

    public static Specification<Product> createProductSpecifications(ProductSearchCriteria criteria) {
        return colorsIn(criteria.getColors())
                .and(priceBetween(criteria.getMinPrice(), criteria.getMaxPrice()))
                .and(nameLike(criteria.getName()))
                .and(starRatingGreaterOrEqual(criteria.getMinStarRating()));
    }

    public static Specification<Product> colorsIn(Optional<Set<String>> colors) {
        if (colors.isEmpty())
            return (root, query, builder) -> builder.conjunction();
        return (root, query, builder) -> colors.map(set -> {
            if (set.isEmpty())
                return null;
            return root.<String> get("color").in(set);
        }).orElse(builder.conjunction());
    }

    public static Specification<Product> priceBetween(Optional<BigDecimal> minPrice, Optional<BigDecimal> maxPrice) {

        return (root, query, builder) -> minPrice.map(min -> {
            return maxPrice.map(max -> builder.between(root.<BigDecimal>get("price"), min, max)
            ).orElse(builder.conjunction());
        }).orElse(builder.conjunction());
    }

    public static Specification<Product> nameLike(Optional<String> name) {

        return (root, query, builder) -> name.map(value -> builder.like(root.<String> get("name"), value)
        ).orElse(builder.conjunction());
    }

    public static Specification<Product> starRatingGreaterOrEqual(Optional<Double> starRating) {

        return (root, query, builder) -> starRating.map(rating ->
                builder.greaterThanOrEqualTo(root.<Double> get("starRating"), rating)
        ).orElse(builder.conjunction());
    }



}


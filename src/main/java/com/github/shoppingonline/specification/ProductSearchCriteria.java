package com.github.shoppingonline.specification;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

public class ProductSearchCriteria {

    private Optional<BigDecimal> minPrice;
    private Optional<BigDecimal> maxPrice;
    private Optional<Double> minStarRating;
    private Optional<Set<String>> colors;
    private Optional<String> name;

    public ProductSearchCriteria(BigDecimal minPrice, BigDecimal maxPrice, Double minStarRating, Set<String> colors, String name) {
        this.minPrice = Optional.ofNullable(minPrice);
        this.maxPrice = Optional.ofNullable(maxPrice);
        this.minStarRating = Optional.ofNullable(minStarRating);
        this.colors = Optional.ofNullable(colors);
        this.name = Optional.ofNullable(name);
    }

    public Optional<BigDecimal> getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Optional<BigDecimal> minPrice) {
        this.minPrice = minPrice;
    }

    public Optional<BigDecimal> getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Optional<BigDecimal> maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Optional<Double> getMinStarRating() {
        return minStarRating;
    }

    public void setMinStarRating(Optional<Double> minStarRating) {
        this.minStarRating = minStarRating;
    }

    public Optional<Set<String>> getColors() {
        return colors;
    }

    public void setColors(Optional<Set<String>> colors) {
        this.colors = colors;
    }

    public Optional<String> getName() {
        return name;
    }

    public void setName(Optional<String> name) {
        this.name = name;
    }

}

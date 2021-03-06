package com.github.shoppingonline.specification;

import java.math.BigDecimal;
import java.util.Set;

public class ProductSearchCriteria {

    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Double minStarRating;
    private Set<String> colors;
    private String name;

    public ProductSearchCriteria(BigDecimal minPrice, BigDecimal maxPrice, Double minStarRating, Set<String> colors, String name) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.minStarRating = minStarRating;
        this.colors = colors;
        this.name = name;
    }

    public BigDecimal getMinPrice() {
        if (minPrice == null)
            return BigDecimal.valueOf(0);
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        if (maxPrice == null)
            return BigDecimal.valueOf(Double.MAX_VALUE);
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Double getMinStarRating() {
        if (minStarRating == null)
            return (double) 0;
        return minStarRating;
    }

    public void setMinStarRating(Double minStarRating) {
        this.minStarRating = minStarRating;
    }

    public Set<String> getColors() {
        if (colors == null)
            return Set.of();
        return colors;
    }

    public void setColors(Set<String> colors) {
        this.colors = colors;
    }

    public String getName() {
        if (name.equals(""))
            return "%";
        return "%" + name.toLowerCase() + "%";
    }

    public void setName(String name) {
        this.name = name;
    }


}

package com.github.shoppingonline.controller;

import com.github.shoppingonline.model.Product;
import com.github.shoppingonline.model.ProductRepository;
import com.github.shoppingonline.specification.ProductSearchCriteria;
import com.github.shoppingonline.specification.ProductSpecification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/products")
class ProductController {

    private final ProductRepository productRepository;

    ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(params = {"!minPrice", "!maxPrice", "!starRating", "!color", "!name"})
    ModelAndView readAllProducts(Pageable page) {
        ModelAndView model = new ModelAndView("/products");
        model.addObject("products", productRepository.findAll(page));

        return model;
    }

    @GetMapping()
    ModelAndView findProducts(ModelAndView model,
                        @RequestParam(required = false)
                    BigDecimal minPrice,

                        @RequestParam(required = false)
                    BigDecimal maxPrice,

                        @RequestParam(required = false)
                    Double starRating,

                        @RequestParam(name="color", required = false)
                    Set<String> colors,

                        @RequestParam(name="name", required = false)
                    String name, Pageable page) {

        ProductSearchCriteria searchCriteria = new ProductSearchCriteria(
                minPrice,
                maxPrice,
                starRating,
                colors,
                name);


            System.out.println("minPrice " + minPrice + ", " + "max " + maxPrice);
            System.out.println(searchCriteria.getMinPrice());
        System.out.println(searchCriteria.getMaxPrice());
        System.out.println(searchCriteria.getName());

            Specification<Product> spec = ProductSpecification.createProductSpecifications(searchCriteria);
            System.out.println("Col size " + productRepository.findAll(spec).size());
            model.addObject("products", productRepository.findAll(spec));
        //return ResponseEntity.ok(productRepository.findAll(page).getContent());
        return model;

        }

    @GetMapping("/{id}")
    ResponseEntity<Product> readProduct(@PathVariable int id) {

        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<Product> createProduct(@RequestBody @Valid Product toCreate) {
        Product result = productRepository.save(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

}

package com.tecnm.merida.market_backendjavaj.web.controller;

import com.tecnm.merida.market_backendjavaj.domain.Product;
import com.tecnm.merida.market_backendjavaj.domain.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Tag(name = "Product", description = "Manage your products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    @Operation(summary = "Get all products", description = "Return a list of all available products")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of products")
    public ResponseEntity<List<Product>> getAll() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    @Operation(summary = "Get products by category", description = "Return all products in a specific category")
    public ResponseEntity<List<Product>> getByCategory(
            @Parameter(description = "Category ID", example = "7", required = true)
            @PathVariable("id") int categoryId) {
        return productService.getByCategory(categoryId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a product by ID")
    public ResponseEntity<Product> getProduct(@PathVariable("id") int productId) {
        return productService.getProduct(productId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    @Operation(summary = "Create a new product")
    public ResponseEntity<Product> save(@RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing product")
    public ResponseEntity<Product> update(@PathVariable("id") int productId, @RequestBody Product product) {
        return productService.getProduct(productId)
                .map(existing -> {
                    product.setProductId(productId);
                    return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product by ID")
    public ResponseEntity<Void> delete(@PathVariable("id") int productId) {
        if (productService.delete(productId)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
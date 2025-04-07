package jva.dev.foodordersystem.controller;

import jva.dev.foodordersystem.domain.entity.Product;
import jva.dev.foodordersystem.repository.ProductRepository;
import jva.dev.foodordersystem.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
          return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.createProduct(product));
    }

    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Long id) {
        return null;
    }



}

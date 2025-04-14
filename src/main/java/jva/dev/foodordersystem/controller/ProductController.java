package jva.dev.foodordersystem.controller;

import jva.dev.foodordersystem.domain.entity.Product;
import jva.dev.foodordersystem.dto.request.ProductRequestDTO;
import jva.dev.foodordersystem.dto.response.ProductResponseDTO;
import jva.dev.foodordersystem.repository.ProductRepository;
import jva.dev.foodordersystem.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
          return new ResponseEntity<>((productService.getAllProducts()), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO product) {
        return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
    }


    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Long id) {
        return null;
    }




}

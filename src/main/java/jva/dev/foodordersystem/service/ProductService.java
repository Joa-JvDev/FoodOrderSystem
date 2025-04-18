package jva.dev.foodordersystem.service;

import jva.dev.foodordersystem.domain.entity.Product;
import jva.dev.foodordersystem.dto.request.ProductRequestDTO;
import jva.dev.foodordersystem.dto.response.ProductResponseDTO;
import jva.dev.foodordersystem.mapper.ProductMapper;
import jva.dev.foodordersystem.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    public List<ProductResponseDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return productMapper.toResponseDTOList(products);
    }

    public ProductResponseDTO createProduct(ProductRequestDTO product) {
        Product productEntity = productMapper.toEntity(product);
        productRepository.save(productEntity);
        return productMapper.toResponseDTO(productEntity);
    }

    public Product updateProduct(Product product, Long id) {
        return null;
    }

}

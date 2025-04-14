package jva.dev.foodordersystem.mapper;

import jva.dev.foodordersystem.domain.entity.Product;
import jva.dev.foodordersystem.dto.request.ProductRequestDTO;
import jva.dev.foodordersystem.dto.response.ProductResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ProductMapper {

    private final ModelMapper modelMapper;

    public ProductResponseDTO toResponseDTO(Product product) {
        return modelMapper.map(product, ProductResponseDTO.class);
    }

    public Product toEntity(ProductRequestDTO productRequestDTO) {
        return modelMapper.map(productRequestDTO, Product.class);
    }

    public List<ProductResponseDTO> toResponseDTOList(List<Product> products) {
        return products.stream()
                .map(this::toResponseDTO)
                .toList();
    }

}

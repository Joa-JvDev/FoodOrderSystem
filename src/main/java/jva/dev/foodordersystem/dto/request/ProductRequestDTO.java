package jva.dev.foodordersystem.dto.request;

import jva.dev.foodordersystem.domain.entity.Category;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequestDTO {
    private String name;
    private String description;
    private BigDecimal price;
    //private Category category;
    private Integer stock;
}

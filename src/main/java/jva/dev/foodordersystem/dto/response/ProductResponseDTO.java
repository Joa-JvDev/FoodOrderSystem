package jva.dev.foodordersystem.dto.response;

import jva.dev.foodordersystem.domain.enums.StatusProduct;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponseDTO {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private StatusProduct status;

}

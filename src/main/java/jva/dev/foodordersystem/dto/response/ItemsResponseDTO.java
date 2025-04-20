package jva.dev.foodordersystem.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ItemsResponseDTO {

    private Integer quantity;
    private BigDecimal unitPrice;
    private ProductResponseDTO product;

}

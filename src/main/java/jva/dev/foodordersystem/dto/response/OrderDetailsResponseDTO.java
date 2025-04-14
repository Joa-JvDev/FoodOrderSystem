package jva.dev.foodordersystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDetailsResponseDTO {
    private Integer quantity;
    private BigDecimal unitPrice;
    private String nameProduct;
    private String description;
}

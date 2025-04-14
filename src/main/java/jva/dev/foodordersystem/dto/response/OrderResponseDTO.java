package jva.dev.foodordersystem.dto.response;

import jva.dev.foodordersystem.domain.enums.StatusOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderResponseDTO {

    private LocalDate orderDate;
    private StatusOrder status;
    private BigDecimal totalPrice;
    private List<OrderDetailsResponseDTO> orderDetails;

}

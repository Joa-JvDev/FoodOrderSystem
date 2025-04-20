package jva.dev.foodordersystem.dto.response;

import jva.dev.foodordersystem.domain.entity.Items;
import lombok.Data;

import java.util.List;

@Data
public class ShoppingCartResponseDTO {
    private List<ItemsResponseDTO> items;
}

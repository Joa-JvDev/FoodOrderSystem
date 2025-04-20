package jva.dev.foodordersystem.mapper;

import jva.dev.foodordersystem.domain.entity.Items;
import jva.dev.foodordersystem.domain.entity.Product;
import jva.dev.foodordersystem.domain.entity.ShoppingCart;
import jva.dev.foodordersystem.dto.response.ItemsResponseDTO;
import jva.dev.foodordersystem.dto.response.ProductResponseDTO;
import jva.dev.foodordersystem.dto.response.ShoppingCartResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

import java.util.List;


@RequiredArgsConstructor
@Component
public class ShoppingCartMapper {
    private final ModelMapper modelMapper;

    public ShoppingCartResponseDTO toResponseDTO(ShoppingCart shoppingCart) {
        ShoppingCartResponseDTO shoppingCartResponseDTO = modelMapper.map(shoppingCart, ShoppingCartResponseDTO.class);
        shoppingCartResponseDTO.setItems(toResponseItemsList(shoppingCart.getItems()));
        return shoppingCartResponseDTO;
    }

    public List<ItemsResponseDTO> toResponseItemsList(List<Items> items) {
        return items.stream()
                .map(this::toResponseItemsDTO)
                .toList();
    }

    public ItemsResponseDTO toResponseItemsDTO(Items items) {
        ItemsResponseDTO itemsResponseDTO = modelMapper.map(items, ItemsResponseDTO.class);
        itemsResponseDTO.setProduct(toProductResponseDTO(items.getProduct()));
        return itemsResponseDTO;
    }


    public ProductResponseDTO toProductResponseDTO(Product product) {
        return modelMapper.map(product, ProductResponseDTO.class);
    }

}

package jva.dev.foodordersystem.mapper;

import jva.dev.foodordersystem.domain.entity.OrderDetails;
import jva.dev.foodordersystem.domain.entity.Orders;
import jva.dev.foodordersystem.domain.entity.Product;
import jva.dev.foodordersystem.dto.response.OrderDetailsResponseDTO;
import jva.dev.foodordersystem.dto.response.OrderResponseDTO;
import jva.dev.foodordersystem.dto.response.ProductResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final ModelMapper modelMapper;

    public OrderResponseDTO toResponseDTO(Orders order) {
        OrderResponseDTO orderResponseDTO = modelMapper.map(order, OrderResponseDTO.class);
        orderResponseDTO.setOrderDetails(toResponseDTOList(order.getDetails()));
        return orderResponseDTO;
    }

    public List<OrderDetailsResponseDTO> toResponseDTOList(List<OrderDetails> orderDetailsList) {
        return orderDetailsList.stream()
                .map(this::toOrderDetailsResponseDTO)
                .toList();
    }

    public OrderDetailsResponseDTO toOrderDetailsResponseDTO(OrderDetails orderDetails) {
        OrderDetailsResponseDTO dto = modelMapper.map(orderDetails, OrderDetailsResponseDTO.class);

        if (orderDetails.getItems() != null && orderDetails.getItems().getProduct() != null) {
            dto.setNameProduct(orderDetails.getItems().getProduct().getName());
            dto.setDescription(orderDetails.getItems().getProduct().getDescription());
        }

        return dto;
    }



}

package jva.dev.foodordersystem.mapper;

import jva.dev.foodordersystem.domain.entity.ShoppingCart;
import jva.dev.foodordersystem.dto.response.ShoppingCartResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;


@RequiredArgsConstructor
@Component
public class ShoppingCartMapper {
    private final ModelMapper modelMapper;

    public ShoppingCartResponseDTO toResponseDTO(ShoppingCart shoppingCart) {
        return modelMapper.map(shoppingCart, ShoppingCartResponseDTO.class);
    }

}

// EXAMPLES
    /*public DistrictResponseDTO toResponseDto(District district) {
        return modelMapper.map(district, DistrictResponseDTO.class);
    }

    public List<DistrictResponseDTO> toResponseDtoList(List<District> districts) {
        return districts.stream()
                .map(this::toResponseDto)
                .toList();
    }*/


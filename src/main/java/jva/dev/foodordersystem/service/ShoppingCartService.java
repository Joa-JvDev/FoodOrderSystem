package jva.dev.foodordersystem.service;

import jakarta.persistence.EntityNotFoundException;
import jva.dev.foodordersystem.domain.entity.Items;
import jva.dev.foodordersystem.domain.entity.Product;
import jva.dev.foodordersystem.domain.entity.ShoppingCart;
import jva.dev.foodordersystem.domain.entity.UserEntity;
import jva.dev.foodordersystem.dto.response.ShoppingCartResponseDTO;
import jva.dev.foodordersystem.mapper.ShoppingCartMapper;
import jva.dev.foodordersystem.service.repository.ProductRepository;
import jva.dev.foodordersystem.service.repository.ShoppingCartRepository;
import jva.dev.foodordersystem.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ShoppingCartService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartMapper shoppingCartMapper;

    public ShoppingCartResponseDTO addProduct(Long id, Integer quantity) {
        UserEntity user = findAutenticatedUser();
        Product product = productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        ShoppingCart shoppingCart = user.getCart();

        Items items = new Items();
        items.setProduct(product);
        items.setQuantity(quantity);
        items.setUnitPrice(product.getPrice());

        shoppingCart.getItems().add(items);
        ShoppingCart updatedShoppingCart = shoppingCartRepository.save(shoppingCart);
        return shoppingCartMapper.toResponseDTO(updatedShoppingCart);
    }

    public ShoppingCartResponseDTO clear() {
        UserEntity user = findAutenticatedUser();
        ShoppingCart shoppingCart = user.getCart();
        shoppingCart.cleanCart();
        ShoppingCart updatedShoppingCart = shoppingCartRepository.save(shoppingCart);
        return shoppingCartMapper.toResponseDTO(updatedShoppingCart);
    }

    public ShoppingCartResponseDTO view() {
        UserEntity user = findAutenticatedUser();
        ShoppingCart shoppingCart = user.getCart();
        return shoppingCartMapper.toResponseDTO(shoppingCart);
    }

    public ShoppingCartResponseDTO itemQuantityModify(Long id, Integer quantity) {
        UserEntity user = findAutenticatedUser();
        ShoppingCart shoppingCart = user.getCart();

        for (Items item : shoppingCart.getItems()) {
            if (item.getId().equals(id)) {
                item.setQuantity(quantity);
            }
        }
        shoppingCartRepository.save(shoppingCart);
        return shoppingCartMapper.toResponseDTO(shoppingCart);
    }

    private UserEntity findAutenticatedUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userRepository.findUserEntityByEmail(auth.getName()).orElseThrow(EntityNotFoundException::new);
        return user;
    }





}

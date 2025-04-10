package jva.dev.foodordersystem.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jva.dev.foodordersystem.domain.entity.Items;
import jva.dev.foodordersystem.domain.entity.Product;
import jva.dev.foodordersystem.domain.entity.ShoppingCart;
import jva.dev.foodordersystem.domain.entity.UserEntity;
import jva.dev.foodordersystem.repository.ProductRepository;
import jva.dev.foodordersystem.repository.ShoppingCartRepository;
import jva.dev.foodordersystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ShoppingCartService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCart addProduct(Long id, Integer quantity) {
        UserEntity user = findAutenticatedUser();
        Product product = productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        ShoppingCart shoppingCart = user.getCart();

        Items items = new Items();
        items.setProduct(product);
        items.setQuantity(quantity);
        items.setUnitPrice(product.getPrice());

        shoppingCart.getItems().add(items);
        return shoppingCartRepository.save(shoppingCart);
    }

    public ShoppingCart clear() {
        UserEntity user = findAutenticatedUser();
        ShoppingCart shoppingCart = user.getCart();
        shoppingCart.cleanCart();
        return shoppingCartRepository.save(shoppingCart);
    }

    public ShoppingCart view() {
        UserEntity user = findAutenticatedUser();
        ShoppingCart shoppingCart = user.getCart();
        return shoppingCart;
    }

    private UserEntity findAutenticatedUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userRepository.findUserEntityByEmail(auth.getName()).orElseThrow(EntityNotFoundException::new);
        return user;
    }



}

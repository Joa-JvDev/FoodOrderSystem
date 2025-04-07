package jva.dev.foodordersystem.service;

import jva.dev.foodordersystem.domain.entity.ShoppingCart;
import jva.dev.foodordersystem.domain.entity.UserEntity;
import jva.dev.foodordersystem.domain.enums.Role;
import jva.dev.foodordersystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity register(UserEntity user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        user.setCart(shoppingCart);
        user.setRole(Role.USER);
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        return userRepository.save(user);
    }

    public UserEntity login() {
        return null;
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

}

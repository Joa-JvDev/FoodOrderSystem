package jva.dev.foodordersystem.controller;

import jva.dev.foodordersystem.domain.entity.ShoppingCart;
import jva.dev.foodordersystem.dto.response.ShoppingCartResponseDTO;
import jva.dev.foodordersystem.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @PostMapping("/add/product")
    public ResponseEntity<ShoppingCartResponseDTO> addProduct(
            @RequestParam Long id,
            @RequestParam Integer quantity)
    {
        return new ResponseEntity<>(shoppingCartService.addProduct(id,quantity), HttpStatus.OK);
    }

    @DeleteMapping("/clear")
    public ResponseEntity<ShoppingCartResponseDTO> clear() {
        return new ResponseEntity<>(shoppingCartService.clear(), HttpStatus.OK);
    }

    @GetMapping("/view")
    public ResponseEntity<ShoppingCartResponseDTO> view() {
        return new ResponseEntity<>(shoppingCartService.view(), HttpStatus.OK);
    }

    @PutMapping("/item/update")
    public ResponseEntity<ShoppingCartResponseDTO> itemQuantityModify(
            @RequestParam Long id,
            @RequestParam Integer quantity) {
        return new ResponseEntity<>(shoppingCartService.itemQuantityModify(id,quantity), HttpStatus.OK);
    }

}

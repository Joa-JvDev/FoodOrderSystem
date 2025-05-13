package jva.dev.foodordersystem.domain.entity;

import jakarta.persistence.*;
import jva.dev.foodordersystem.domain.enums.StatusProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private BigDecimal price;
    private StatusProduct status;
    private Integer stock;
/*
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
*/
}

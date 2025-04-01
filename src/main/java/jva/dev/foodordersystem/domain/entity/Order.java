package jva.dev.foodordersystem.domain.entity;

import jakarta.persistence.*;
import jva.dev.foodordersystem.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate orderDate;
    private Status status;
    private BigDecimal totalPrice;

    @OneToMany
    @JoinColumn(name = "pedido_id")
    private List<OrderDetails> details = new ArrayList<>();

}

package jva.dev.foodordersystem.domain.entity;

import jakarta.persistence.*;
import jva.dev.foodordersystem.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private String email;
    private String password;
    @Column(name = "is_enable")
    private boolean isEnabled;
    @Column(name = "account_No_Expired")
    private boolean accountNonExpired;
    @Column(name = "account_No_Locked")
    private boolean accountNonLocked;
    @Column(name = "credential_No_Expired")
    private boolean credentialsNonExpired;
    private String phone;
    private String address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private ShoppingCart cart;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "users_id")
    private List<Orders> orders = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Role role;



}

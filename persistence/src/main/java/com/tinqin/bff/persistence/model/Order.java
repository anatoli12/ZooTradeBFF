package com.tinqin.bff.persistence.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "order_id", nullable = false)
    private UUID orderId;

    private Timestamp createDate;

    private BigDecimal totalPrice;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "order")
    private List<CartItem> cartItems;
}

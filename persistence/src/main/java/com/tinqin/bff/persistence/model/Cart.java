package com.tinqin.bff.persistence.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "carts")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "cart_id", nullable = false)
    private UUID id;


    @OneToMany
    private List<CartItem> cartItems;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime dateCreated;

    @Transient
    public int getNumberOfProducts() {
        return this.cartItems.size();
    }
}

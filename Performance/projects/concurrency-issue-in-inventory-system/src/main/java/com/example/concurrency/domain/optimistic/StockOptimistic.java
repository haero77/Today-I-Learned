package com.example.concurrency.domain.optimistic;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StockOptimistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    private Long id;

    private long quantity;

    @Version
    private Integer version;

    public StockOptimistic(final long quantity) {
        this.quantity = quantity;
    }

    public void decreaseQuantity(final long quantityToDecrease) {
        final long decreasedQuantity = this.quantity - quantityToDecrease;

        if (decreasedQuantity < 0) {
            throw new IllegalArgumentException();
        }

        this.quantity = decreasedQuantity;
    }
}

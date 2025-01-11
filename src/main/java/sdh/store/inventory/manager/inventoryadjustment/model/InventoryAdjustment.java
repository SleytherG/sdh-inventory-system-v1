package sdh.store.inventory.manager.inventoryadjustment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sdh.store.inventory.manager.product.model.Product;
import sdh.store.inventory.manager.user.model.User;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "inventory_adjustment")
public class InventoryAdjustment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "adjusted_quantity")
    private Integer adjustedQuantity;

    @Column(length = 1000)
    private String description;

    @Column(name = "adjustment_date", nullable = false)
    private LocalDateTime adjustmentDate = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


}

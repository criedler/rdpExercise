package htl.steyr.rdp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "apartment")
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable=false)
    private Category category;
}

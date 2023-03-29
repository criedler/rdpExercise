package htl.steyr.rdp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "supplementary_package")
public class SupplementaryPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "daily_price", nullable = false)
    private BigDecimal dailyPrice;

    @OneToMany(mappedBy = "supplementaryPackage", cascade = CascadeType.ALL)
    private List<BookingToSupplementaryPackage> supplementaryPackages;
}

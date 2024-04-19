package htl.steyr.rdp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "booking_package")
public class BookingPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "supplementary_package_id", nullable = false)
    private SupplementaryPackage supplementaryPackage;

    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "quantity", nullable = false)
    private int quantity;

}

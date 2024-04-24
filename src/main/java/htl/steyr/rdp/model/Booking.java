package htl.steyr.rdp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.PERSIST)
    private List<ApartmentBooking> apartmentBookings;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.PERSIST)
    private List<BookingPackage> bookingPackages;

    public BigDecimal calculateTotalPrice() {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (ApartmentBooking apartmentBooking : apartmentBookings) {
            totalPrice = totalPrice.add(apartmentBooking.getApartment().getPrice());
        }
        for (BookingPackage bookingPackage : bookingPackages) {
            totalPrice = totalPrice.add(bookingPackage.getSupplementaryPackage().getPrice());
        }
        return totalPrice;
    }
}

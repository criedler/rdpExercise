package htl.steyr.rdp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private List<BookingToApartment> apartments;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private List<BookingToSupplementaryPackage> packages;

    @Column(name = "done", columnDefinition = "tinyint(1) default 0")
    private boolean done = false;

    public BigDecimal calculatePrice() {
        double price = 0;

        for (BookingToApartment apartment : apartments) {
            long days = TimeUnit.DAYS.convert(apartment.getEndDate().getTime() - apartment.getStartDate().getTime(), TimeUnit.MILLISECONDS);
            price += days * apartment.getApartment().getDailyPrice().doubleValue();
        }

        for (BookingToSupplementaryPackage selPackage : packages) {
            long days = TimeUnit.DAYS.convert(selPackage.getEndDate().getTime() - selPackage.getStartDate().getTime(), TimeUnit.MILLISECONDS);
            price += days * selPackage.getSupplementaryPackage().getDailyPrice().doubleValue();
        }

        return new BigDecimal(price);
    }
}

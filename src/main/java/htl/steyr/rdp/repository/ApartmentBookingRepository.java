package htl.steyr.rdp.repository;

import htl.steyr.rdp.model.ApartmentBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApartmentBookingRepository extends JpaRepository<ApartmentBooking, Long> {
}

package htl.steyr.rdp.repository;

import htl.steyr.rdp.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}

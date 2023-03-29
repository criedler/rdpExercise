package htl.steyr.rdp.repository;

import htl.steyr.rdp.model.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
    //@Query(value="SELECT a FROM Apartment a JOIN BookingToApartment ba WHERE ba.startDate")

    //@Query(value = "SELECT c.* FROM ratp_cars c LEFT OUTER JOIN ratp_rentals r ON (r.car_id = c.id AND ((r.date_to >= ?1 AND r.date_to <= ?2) OR (r.date_from >= ?1 AND r.date_from <= ?2) OR (r.date_from <= ?1 AND r.date_to >= ?2))) WHERE r.id IS NULL GROUP BY c.id", nativeQuery = true)
    @Query(value="SELECT a FROM apartment a " +
            "LEFT OUTER JOIN booking_to_apartment ba " +
            "ON (a.id = ba.apartment_id AND " +
            "    ((ba.end_date >= ?1 AND ba.end_date <= ?2) OR " +
            "     (ba.start_date >= ?1 AND ba.start_date <= ?2) OR " +
            "     (ba.start_date <= ?1 AND ba.end_date >= ?2))) " +
            "WHERE (ba.id IS NULL)", nativeQuery = true)
    List<Apartment> getFreeApartments(Date start, Date end);
}

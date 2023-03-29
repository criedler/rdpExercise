package htl.steyr.rdp.repository;

import htl.steyr.rdp.model.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

    @Query(value="SELECT a.* FROM apartment a " +
            "LEFT OUTER JOIN booking_to_apartment ba " +
            "ON (a.id = ba.apartment_id AND " +
            "    ((ba.end_date >= ?1 AND ba.end_date <= ?2) OR " +
            "     (ba.start_date >= ?1 AND ba.start_date <= ?2) OR " +
            "     (ba.start_date <= ?1 AND ba.end_date >= ?2))) " +
            "WHERE (ba.id IS NULL)", nativeQuery = true)
    List<Apartment> getFreeApartments(Date start, Date end);
}

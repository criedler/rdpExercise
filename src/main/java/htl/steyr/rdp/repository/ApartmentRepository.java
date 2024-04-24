package htl.steyr.rdp.repository;

import htl.steyr.rdp.dto.ApartmentResponseDto;
import htl.steyr.rdp.model.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {



  @Query(value = "SELECT * FROM apartment a\n" +
    "   WHERE a.id NOT IN (\n" +
    "   SELECT a.id FROM apartment a\n" +
    "   LEFT OUTER JOIN apartment_booking ab on a.id = ab.apartment_id\n" +
    "   WHERE NOT(aB.end_date < :startDate OR aB.start_date > :endDate))", nativeQuery = true)
  List<Apartment> findFreeApartments(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}

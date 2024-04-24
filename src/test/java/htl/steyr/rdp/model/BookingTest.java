package htl.steyr.rdp.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookingTest {

  @Test
  void calculateTotalPrice() {
    Booking booking = new Booking();

    ApartmentBooking apartmentBooking = new ApartmentBooking();
    Apartment apartment = new Apartment();
    apartment.setPrice(BigDecimal.valueOf(1));
    apartmentBooking.setApartment(apartment);
    List<ApartmentBooking> apartmentBookingList = List.of(apartmentBooking);
    booking.setApartmentBookings(apartmentBookingList);

    BookingPackage bookingPackage = new BookingPackage();
    SupplementaryPackage supplementaryPackage = new SupplementaryPackage();
    supplementaryPackage.setPrice(BigDecimal.valueOf(1));
    bookingPackage.setSupplementaryPackage(supplementaryPackage);

    List<BookingPackage> bookingPackageList = List.of(bookingPackage);
    booking.setBookingPackages(bookingPackageList);

    assertEquals(BigDecimal.valueOf(2), booking.calculateTotalPrice());
  }
}

package htl.steyr.rdp.service;

import htl.steyr.rdp.dto.ApartmentBookingRequestDto;
import htl.steyr.rdp.dto.BookingRequestDto;
import htl.steyr.rdp.dto.PackageRequestDto;
import htl.steyr.rdp.model.*;
import htl.steyr.rdp.repository.*;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class BookingService {

  private final BookingRepository bookingRepository;

  private final CustomerRepository customerRepository;

  private final ApartmentBookingRepository apartmentBookingRepository;

  private final SupplementaryPackageRepository supplementaryPackageRepository;

  private final ApartmentRepository apartmentRepository;

  private final BookingPackageRepository bookingPackageRepository;

  public void createBooking(BookingRequestDto bookingRequestDto) {
    Booking booking = new Booking();

    Optional<Customer> customer = customerRepository.findById(bookingRequestDto.customer_id());
    List<ApartmentBooking> apartmentBookings = new ArrayList<>();

    List<BookingPackage> bookingPackages = new ArrayList<>();
    for (ApartmentBookingRequestDto apartmentBookingRequestDto : bookingRequestDto.apartments()) {
      Optional<Apartment> resultApartment = apartmentRepository.findById(apartmentBookingRequestDto.apartment_id());
      if (resultApartment.isPresent()) {
        Apartment apartment = resultApartment.get();
        ApartmentBooking apartmentBooking = new ApartmentBooking();
        apartmentBooking.setApartment(apartment);
        apartmentBooking.setBooking(booking);
        apartmentBooking.setStartDate(apartmentBookingRequestDto.date_from());
        apartmentBooking.setEndDate(apartmentBookingRequestDto.date_to());
        apartmentBookings.add(apartmentBooking);
      }
    }
    for (PackageRequestDto packageRequestDto : bookingRequestDto.packages()) {
      Optional<SupplementaryPackage> resultSupplementaryPackage = supplementaryPackageRepository.findById(packageRequestDto.package_id());
      if (resultSupplementaryPackage.isPresent()) {
        SupplementaryPackage supplementaryPackage = resultSupplementaryPackage.get();
        BookingPackage bookingPackage = new BookingPackage();
        bookingPackage.setBooking(booking);
        bookingPackage.setSupplementaryPackage(supplementaryPackage);
        bookingPackage.setQuantity(packageRequestDto.amount());
        bookingPackage.setStartDate(packageRequestDto.date_from());
        bookingPackage.setEndDate(packageRequestDto.date_to());
        bookingPackages.add(bookingPackage);
      }
    }

    if (customer.isPresent()){
      booking.setCustomer(customer.get());
      booking.setApartmentBookings(apartmentBookings);
      booking.setBookingPackages(bookingPackages);
      bookingRepository.save(booking);
    }
  }
}

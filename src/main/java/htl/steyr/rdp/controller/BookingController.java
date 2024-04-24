package htl.steyr.rdp.controller;

import htl.steyr.rdp.dto.BookingRequestDto;
import htl.steyr.rdp.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
public class BookingController {

  private final BookingService bookingService;

  public BookingController(BookingService bookingService) {
    this.bookingService = bookingService;
  }


  @PostMapping("/create")
  public ResponseEntity<Void> createBooking(@RequestBody BookingRequestDto bookingRequestDto) {

    try {
      bookingService.createBooking(bookingRequestDto);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      System.out.println(e.getLocalizedMessage());
      return ResponseEntity.badRequest().build();
    }
  }
}

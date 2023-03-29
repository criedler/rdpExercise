package htl.steyr.rdp.controller;

import htl.steyr.rdp.dto.BookingDto;
import htl.steyr.rdp.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private BookingService service;

    @PostMapping("/create")
    public ResponseEntity<BookingDto> create(@RequestBody BookingDto booking) {
        service.save(booking);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}

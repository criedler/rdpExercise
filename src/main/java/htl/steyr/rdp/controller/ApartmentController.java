package htl.steyr.rdp.controller;

import htl.steyr.rdp.dto.ApartmentResponseDto;
import htl.steyr.rdp.service.ApartmentService;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/apartment")
public class ApartmentController {

  private final ApartmentService apartmentService;


  public ApartmentController(ApartmentService apartmentService) {
    this.apartmentService = apartmentService;
  }

  @GetMapping("/available/{startDate}/{endDate}")
  public ResponseEntity<List<ApartmentResponseDto>> getAvailable(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate) {
    try {
      return new ResponseEntity<>(apartmentService.getFreeApartments(startDate, endDate), HttpStatus.OK);
    } catch (Exception e) {
      System.out.println(e.getLocalizedMessage());
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
}

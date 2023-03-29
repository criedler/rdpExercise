package htl.steyr.rdp.controller;

import htl.steyr.rdp.dto.ApartmentDto;
import htl.steyr.rdp.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/apartment")
public class ApartmentController {
    @Autowired
    private ApartmentService service;

    @GetMapping("/available/{start}/{end}")
    public ResponseEntity<List<ApartmentDto>> getFreeApartments(@PathVariable(name = "start") String start, @PathVariable(name = "end") String end) {
        return new ResponseEntity<>(service.getFreeApartments(start, end), HttpStatus.OK);
    }
}

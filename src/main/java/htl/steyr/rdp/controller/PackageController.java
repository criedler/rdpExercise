package htl.steyr.rdp.controller;

import htl.steyr.rdp.dto.ApartmentDto;
import htl.steyr.rdp.dto.SupplementaryPackageDto;
import htl.steyr.rdp.service.ApartmentService;
import htl.steyr.rdp.service.SupplementaryPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/package")
public class PackageController {
    @Autowired
    private SupplementaryPackageService service;

    @GetMapping("/list")
    public ResponseEntity<List<SupplementaryPackageDto>> getFreeApartments() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }
}

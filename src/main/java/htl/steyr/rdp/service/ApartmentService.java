package htl.steyr.rdp.service;

import htl.steyr.rdp.dto.ApartmentResponseDto;
import htl.steyr.rdp.model.Apartment;
import htl.steyr.rdp.model.Category;
import htl.steyr.rdp.repository.ApartmentRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ApartmentService {
  private final ApartmentRepository repo;

  public ApartmentService(ApartmentRepository repo) {
    this.repo = repo;
  }

  public static ApartmentResponseDto map(Apartment apartment){
    return new ApartmentResponseDto(apartment.getName(), apartment.getPrice(), apartment.getCapacity() );
  }


  public List<ApartmentResponseDto> getFreeApartments(LocalDate startDate, LocalDate endDate) {
    return repo.findFreeApartments(startDate, endDate).stream()
      .map(ApartmentService::map)
      .collect(toList());
  }
}

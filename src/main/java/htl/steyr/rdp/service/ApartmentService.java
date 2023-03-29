package htl.steyr.rdp.service;

import htl.steyr.rdp.dto.ApartmentDto;
import htl.steyr.rdp.model.Apartment;
import htl.steyr.rdp.repository.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApartmentService {
    @Autowired
    private ApartmentRepository repo;

    public ApartmentDto map(Apartment in) {
        return new ApartmentDto(in.getId(), in.getName());
    }

    public List<ApartmentDto> getFreeApartments(String from, String to) {
        try {
            java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(from);
            Date start = new Date(utilDate.getTime());
            utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(to);
            Date end = new Date(utilDate.getTime());
            return repo.getFreeApartments(start, end).stream().
                    map(this::map).
                    collect(Collectors.toList());
        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}

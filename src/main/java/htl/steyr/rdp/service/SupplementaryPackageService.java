package htl.steyr.rdp.service;

import htl.steyr.rdp.dto.SupplementaryPackageDto;
import htl.steyr.rdp.model.SupplementaryPackage;
import htl.steyr.rdp.repository.SupplementaryPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplementaryPackageService {



  /**
     * @ToDo
     * 1) Create the SupplementaryPackageService - entity
     * 2) Create the repository
     * 3) Uncomment the code beneath
     */


    @Autowired
    private SupplementaryPackageRepository repo;

    public SupplementaryPackageDto map(SupplementaryPackage in) {
        return new SupplementaryPackageDto(in.getId(), in.getName(), 0);
    }

    public List<SupplementaryPackageDto> findAll() {
      return repo.findAll().stream().
        map(this::map).
        collect(Collectors.toList());
    }
}

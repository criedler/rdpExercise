package htl.steyr.rdp.repository;

import htl.steyr.rdp.model.Apartment;
import htl.steyr.rdp.model.SupplementaryPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SupplementaryPackageRepository extends JpaRepository<SupplementaryPackage, Long> {
    @Query(value="SELECT s FROM SupplementaryPackage s WHERE s.id IN :ids")
    List<SupplementaryPackage> findAllWithIds(List<Long> ids);
}

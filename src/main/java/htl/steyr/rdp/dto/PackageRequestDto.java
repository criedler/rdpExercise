package htl.steyr.rdp.dto;

import java.time.LocalDate;

public record PackageRequestDto(Long package_id, int amount, LocalDate date_from, LocalDate date_to) {
}

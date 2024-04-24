package htl.steyr.rdp.dto;

import java.time.LocalDate;

public record ApartmentBookingRequestDto(Long apartment_id, LocalDate date_from, LocalDate date_to) {
}

package htl.steyr.rdp.dto;

import java.util.List;

public record BookingRequestDto(Long customer_id, List<ApartmentBookingRequestDto> apartments, List<PackageRequestDto> packages) {
}

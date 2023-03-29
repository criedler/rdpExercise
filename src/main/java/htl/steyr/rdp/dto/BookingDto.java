package htl.steyr.rdp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public record BookingDto(Long id,
                         @JsonProperty("customer_id") Long customerId,
                         BookingApartmentDto[] apartments,
                         BookingSupplementaryPackageDto[] packages
                         ) {
}

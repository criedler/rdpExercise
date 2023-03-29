package htl.steyr.rdp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BookingApartmentDto(@JsonProperty("apartment_id") long apartmentId,
                                  @JsonProperty("date_from") String dateFrom,
                                  @JsonProperty("date_to") String dateTo) {
}

package htl.steyr.rdp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BookingSupplementaryPackageDto(@JsonProperty("package_id") long packageId,
                                             int amount,
                                             @JsonProperty("date_from") String dateFrom,
                                             @JsonProperty("date_to") String dateTo) {
}

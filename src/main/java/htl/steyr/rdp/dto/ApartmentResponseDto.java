package htl.steyr.rdp.dto;


import java.math.BigDecimal;

public record ApartmentResponseDto(String name, BigDecimal price, int capacity) {
}

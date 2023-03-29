package htl.steyr.rdp.model;

import htl.steyr.rdp.repository.BookingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookingTest {

    @Autowired
    BookingRepository repo;

    @Test
    void calculatePrice() {
        Optional<Booking> optional = repo.findById(1L);

        assertTrue(optional.isPresent());

        assertEquals(10, optional.get().calculatePrice().doubleValue());
    }
}
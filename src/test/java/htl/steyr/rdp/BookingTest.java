package htl.steyr.rdp;

import htl.steyr.rdp.model.Booking;
import htl.steyr.rdp.repository.BookingRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookingTest {

    @Autowired
    BookingRepository repo;

    @Test
    @Order(1)
    void calculatePrice() {
        Optional<Booking> optional = repo.findById(1L);

        assertTrue(optional.isPresent());

        assertEquals(10, optional.get().calculatePrice().doubleValue());
    }
}
package ru.developeerz.gateway.api.booking;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.developeerz.gateway.api.ApiPaths;
import ru.developeerz.gateway.core.service.BookingService;

@RestController
@RequiredArgsConstructor
public class BookingController {

    public final BookingService bookingService;

    @GetMapping(ApiPaths.BOOKING)
    public ResponseEntity<?> getBookingTable(@Valid @RequestParam("table") String table) {
        return bookingService.getBookingTable(table);
    }

    @PostMapping(ApiPaths.BOOKING)
    public ResponseEntity<?> createBookingTable(@Valid @RequestParam("table") String table) {
        return bookingService.createBookingTable(table);
    }

    @DeleteMapping(ApiPaths.BOOKING)
    public ResponseEntity<?> cancelBookingTable(@Valid @RequestParam("table") String table) {
        return bookingService.cancelBookingTable(table);
    }
}

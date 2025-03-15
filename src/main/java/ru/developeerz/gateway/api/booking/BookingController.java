package ru.developeerz.gateway.api.booking;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.developeerz.gateway.api.ApiPaths;
import ru.developeerz.gateway.api.booking.model.BookingFilter;
import ru.developeerz.gateway.api.booking.model.RequestCreationBookingTable;
import ru.developeerz.gateway.api.booking.model.RequestDeletionBookingTable;
import ru.developeerz.gateway.api.booking.model.Table;
import ru.developeerz.gateway.core.service.BookingService;

@RestController
@RequiredArgsConstructor
public class BookingController {

    public final BookingService bookingService;

    @GetMapping(ApiPaths.BOOKING)
    public ResponseEntity<?> getAvailableTables(@ParameterObject BookingFilter filter) {
        return bookingService.getBookingTables(filter);
    }

    @GetMapping(ApiPaths.BOOKING_BY_ID)
    public ResponseEntity<?> getBookingTimesTable(@ParameterObject Table table) {
        return bookingService.getBookingTable(table);
    }

    @PostMapping(ApiPaths.BOOKING_BY_ID)
    public ResponseEntity<?> createBookingTable(
            @PathVariable(name = "table_id") int tableId,
            @Valid @RequestBody RequestCreationBookingTable request
    ) {
        return bookingService.createBookingTable(tableId, request);
    }

    @DeleteMapping(ApiPaths.BOOKING)
    public ResponseEntity<?> cancelBookingTable(@Valid @RequestBody RequestDeletionBookingTable request) {
        return bookingService.cancelBookingTable(request);
    }
}

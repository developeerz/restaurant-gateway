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

@RestController
@RequiredArgsConstructor
public class BookingController {

    public final BookingFacade bookingFacade;

    @GetMapping(ApiPaths.BOOKING)
    public ResponseEntity<?> getAvailableTables(@ParameterObject BookingFilter filter) {
        return bookingFacade.getBookingTables(filter);
    }

    @GetMapping(ApiPaths.BOOKING_BY_ID)
    public ResponseEntity<?> getBookingTimesTable(@ParameterObject Table table) {
        return bookingFacade.getBookingTable(table);
    }

    @PostMapping(ApiPaths.BOOKING_BY_ID)
    public ResponseEntity<?> createBookingTable(
            @PathVariable(name = "table_id") int tableId,
            @Valid @RequestBody RequestCreationBookingTable request
    ) {
        return bookingFacade.createBookingTable(tableId, request);
    }

    @DeleteMapping(ApiPaths.BOOKING)
    public ResponseEntity<?> cancelBookingTable(@Valid @RequestBody RequestDeletionBookingTable request) {
        return bookingFacade.cancelBookingTable(request);
    }
}

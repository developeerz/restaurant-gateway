package ru.developeerz.gateway.api.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.developeerz.gateway.api.booking.mapper.BookingMapper;
import ru.developeerz.gateway.api.booking.model.BookingFilter;
import ru.developeerz.gateway.api.booking.model.RequestBookingTable;
import ru.developeerz.gateway.api.booking.model.RequestCancelBooking;
import ru.developeerz.gateway.api.booking.model.RequestCreationBookingTable;
import ru.developeerz.gateway.api.booking.model.RequestDeletionBookingTable;
import ru.developeerz.gateway.api.booking.model.ResponseReserveTable;
import ru.developeerz.gateway.api.booking.model.Table;

@Service
@RequiredArgsConstructor
public class BookingFacade {

    private final WebClient bookingWebClient;

    private final BookingMapper mapper;

    public ResponseEntity<?> getBookingTable(Table table) {
        ResponseEntity<ResponseReserveTable> response = bookingWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("api/booking")
                        .queryParam("table", table.table_id())
                        .build())
                .retrieve()
                .toEntity(ResponseReserveTable.class)
                .block();

        return response;
    }

    public ResponseEntity<?> createBookingTable(int tableId, RequestCreationBookingTable request) {
        int userId = (Integer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        RequestBookingTable requestBookingTable = mapper.map(request, userId);

        String uri = "api/booking/" + tableId;
        ResponseEntity<?> response = bookingWebClient.post()
                .uri(uri)
                .bodyValue(requestBookingTable)
                .retrieve()
                .toEntity(ResponseEntity.class)
                .block();

        return response;
    }

    public ResponseEntity<?> cancelBookingTable(RequestDeletionBookingTable request) {
        int userId = (Integer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        RequestCancelBooking requestBookingTable = mapper.map(request, userId);

        ResponseEntity<?> response = bookingWebClient.method(HttpMethod.DELETE)
                .uri("api/booking")
                .bodyValue(requestBookingTable)
                .retrieve()
                .toEntity(ResponseEntity.class)
                .block();

        return response;
    }

    public ResponseEntity<?> getBookingTables(BookingFilter filter) {
        ResponseEntity<?> response = bookingWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("api/booking")
                        .queryParam("date", filter.date())
                        .queryParam("duration", filter.duration())
                        .queryParam("seats", filter.seats())
                        .build())
                .retrieve()
                .toEntity(ResponseEntity.class)
                .block();

        return response;
    }
}

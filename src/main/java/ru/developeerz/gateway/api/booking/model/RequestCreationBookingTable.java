package ru.developeerz.gateway.api.booking.model;

import java.time.LocalDate;

public record RequestCreationBookingTable(
        LocalDate date,
        int duration
) {
}

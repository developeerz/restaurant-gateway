package ru.developeerz.gateway.api.booking.model;

import java.time.LocalDate;

public record RequestBookingTable(
        int userId,
        int tableId,
        LocalDate date,
        int duration
) {
}

package ru.developeerz.gateway.api.booking.model;

import java.time.LocalDate;

public record RequestBookingTable(
        int userId,
        LocalDate date,
        int duration
) {
}

package ru.developeerz.gateway.api.booking.model;

import java.time.LocalDate;

public record BookingAvailability(
        LocalDate date,
        boolean reserved
) {
}

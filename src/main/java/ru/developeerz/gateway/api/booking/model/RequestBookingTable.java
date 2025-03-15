package ru.developeerz.gateway.api.booking.model;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RequestBookingTable(

        int userId,

        @NotNull
        LocalDate date,

        int duration
) {
}

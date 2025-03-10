package ru.developeerz.gateway.api.booking.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record BookingFilter(

        Integer duration,

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate date,

        @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
        LocalDate time,

        Integer seats
) {
}

package ru.developeerz.gateway.api.booking.model;

import java.util.List;

public record ResponseReserveTable(
       List<BookingAvailability> availability
) {

}

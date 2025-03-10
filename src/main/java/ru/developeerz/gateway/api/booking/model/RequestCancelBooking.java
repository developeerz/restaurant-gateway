package ru.developeerz.gateway.api.booking.model;

public record RequestCancelBooking(
        int userId,
        String bookingNumber
) {
}

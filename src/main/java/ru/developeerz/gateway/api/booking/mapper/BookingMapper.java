package ru.developeerz.gateway.api.booking.mapper;

import org.mapstruct.Mapper;
import ru.developeerz.gateway.api.booking.model.RequestBookingTable;
import ru.developeerz.gateway.api.booking.model.RequestCancelBooking;
import ru.developeerz.gateway.api.booking.model.RequestCreationBookingTable;
import ru.developeerz.gateway.api.booking.model.RequestDeletionBookingTable;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    RequestBookingTable map(RequestCreationBookingTable requestCreationBookingTable, int userId);

    RequestCancelBooking map(RequestDeletionBookingTable requestDeletionBookingTable, int userId);
}

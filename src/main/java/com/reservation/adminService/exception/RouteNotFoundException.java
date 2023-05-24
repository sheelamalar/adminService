package com.reservation.adminService.exception;

import com.reservation.adminService.constants.ReservationConstants;

public class RouteNotFoundException extends ReservationException {

	public RouteNotFoundException() {
        super("Pls check the bus numner,"+ ReservationConstants.RESERVATION_ROUTE_NOTAVAILABLE);
    }

    public RouteNotFoundException (String message) {
        super(message + ReservationConstants.RESERVATION_ROUTE_NOTAVAILABLE);
    }
}

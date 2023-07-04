package com.NikolaTabas94rn.cinema.exceptions;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorInfo {
    ErrorType errorType;
    ResourceType resourceType;

    String message;

    public enum ErrorType {
        BAD_REQUEST,
        NOT_FOUND,
        UNKNOWN,
        UNIQUE_VIOLATION,
    }

    public enum ResourceType {
        AUDITORIUM,
        MOVIE,
        RESERVATION,
        SCREENING,
        SEAT,
        SEATRESERVED,
        USER,
    }
}

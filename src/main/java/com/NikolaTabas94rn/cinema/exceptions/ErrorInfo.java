package com.NikolaTabas94rn.cinema.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorInfo {
    @Schema(description = "Type of error", example = "NOT_FOUND")
    ErrorType errorType;
    @Schema(description = "Type of resource for which error occurred", example = "MOVIE")
    ResourceType resourceType;
    @Schema(description = "Additional message for error", example = "Movie with given `id` was not found")
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

package com.NikolaTabas94rn.cinema.model.api.user;

import com.NikolaTabas94rn.cinema.model.entity.ReservationEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@Builder
@Schema(name = "User")
@RequiredArgsConstructor
public class UserDto {
    @Schema(description = "User identifier", example = "1")
    int id;
    @Schema(description = "User's email. Must be unique", example = "pera.peric@example.com")
    String email;

}

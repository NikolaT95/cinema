package com.NikolaTabas94rn.cinema.model.api.user;

import com.NikolaTabas94rn.cinema.model.entity.ReservationEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
@Value
@Builder
@Schema(name = "UserSave")
public class UserSaveDto {
    @Schema(description = "User's email. Must be unique", example = "pera.peric@example.com")
    @Email
    @NotBlank
    String email;
    @Schema(description = "User's password", example = "123456")
    @NotEmpty
    @Size(min = 6)
    String password;

}

package com.NikolaTabas94rn.cinema.model.api.user;

import com.NikolaTabas94rn.cinema.model.entity.ReservationEntity;
import lombok.Builder;
import lombok.Value;

import java.util.List;
@Value
@Builder
public class UserSaveDto {

    String email;

    String password;

}

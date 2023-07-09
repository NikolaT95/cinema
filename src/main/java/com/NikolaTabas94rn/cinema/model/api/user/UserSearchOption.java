package com.NikolaTabas94rn.cinema.model.api.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSearchOption {
    Integer page;

    Integer pageSize;
}

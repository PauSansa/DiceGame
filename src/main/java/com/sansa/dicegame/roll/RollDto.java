package com.sansa.dicegame.roll;

import com.sansa.dicegame.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RollDto {
    private Integer id;
    private UserDto user;
    private Integer dice1;
    private Integer dice2;
    private Date rolledAt;
    private String result;
}

package com.sansa.dicegame.user.dto;


import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Integer userId;
    private String username;
    private Date createdAt;
    private int rollsPlayed;
    private int wins;
    private float winRate;
}

package com.sansa.dicegame.roll;


import com.sansa.dicegame.user.dto.UserToDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class RollToDto implements Function<Roll,RollDto> {
    private final UserToDto userToDto;
    @Override
    public RollDto apply(Roll roll) {
        return RollDto.builder()
                .id(roll.getId())
                .user(userToDto.apply(roll.getUser()))
                .dice1(roll.getDice1())
                .dice2(roll.getDice2())
                .result(calculateResult(roll))
                .rolledAt(roll.getRolledAt())
                .build();
    }

    private String calculateResult(Roll roll) {
        return ((roll.getDice1() + roll.getDice2()) == 7) ? "Winner" : "Looser";
    }
}

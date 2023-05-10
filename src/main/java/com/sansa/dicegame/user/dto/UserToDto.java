package com.sansa.dicegame.user.dto;

import com.sansa.dicegame.roll.Roll;
import com.sansa.dicegame.roll.RollRepository;
import com.sansa.dicegame.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class UserToDto implements Function<User,UserDto> {
    private final RollRepository rollRepository;

    @Override
    public UserDto apply(User user) {
        return UserDto.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .createdAt(user.getCreatedAt())
                .winRate(calculateWinrate(user))
                .rollsPlayed(calculateRollsPlayed(user))
                .wins(calculateWins(user))
                .build();
    }

    private int calculateRollsPlayed(User user){
        return rollRepository.findAllByUser(user).size();
    }

    private int calculateWins(User user){
        List<Roll> rolls = rollRepository.findAllByUser(user);
        return (int) rolls.stream().filter((roll -> roll.getDice1() + roll.getDice2() == 7))
                .count();
    }

    public float calculateWinrate(User user){
        List<Roll> rolls = rollRepository.findAllByUser(user);
        float winCount = rolls.stream().filter((roll -> roll.getDice1() + roll.getDice2() == 7))
                .count();

        return (calculateRollsPlayed(user) == 0) ? 0f : (float) calculateWins(user) / calculateRollsPlayed(user) * 100f;
    }
}

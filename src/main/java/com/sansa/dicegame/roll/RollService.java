package com.sansa.dicegame.roll;

import com.sansa.dicegame.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class RollService {
    private final RollRepository rollRepository;
    private final RollToDto toDto;
    private final Random random;
    public RollDto createRoll(User user){
        Roll roll = Roll.builder()
                .dice1(randomDice())
                .dice2(randomDice())
                .rolledAt(new Date())
                .user(user)
                .build();
        return toDto.apply(rollRepository.save(roll));
    }

    private Integer randomDice() {
        return (random.nextInt(6) +1);
    }


    public List<RollDto> getAllRolls(){
        return rollRepository.findAll().stream().map(toDto).toList();
    }


    public List<RollDto> getTenRolls(User user){
        return rollRepository.findTop10ByUserOrderByRolledAtDesc(user).stream().map(toDto).toList();
    }

    @Transactional
    public void deleteRollsByUser(User user) {
        rollRepository.deleteAllByUser(user);
    }


}

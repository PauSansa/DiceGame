package com.sansa.dicegame.controller;

import com.sansa.dicegame.roll.RollDto;
import com.sansa.dicegame.roll.RollService;
import com.sansa.dicegame.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/game")
@RequiredArgsConstructor
public class ApiController {
    private final RollService rollService;

    @DeleteMapping("/rolls")
    public ResponseEntity<?> deleteAllRolls(){
        User user = getUser();
        rollService.deleteRollsByUser(user);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/roll")
    public RollDto createRoll(){
        User user = getUser();
        return rollService.createRoll(user);
    }

    @GetMapping("/roll")
    public List<RollDto> getTenRolls(){
        User user = getUser();
        return rollService.getAllRolls();
    }

    public User getUser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}

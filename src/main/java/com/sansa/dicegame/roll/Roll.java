package com.sansa.dicegame.roll;


import com.sansa.dicegame.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Roll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "playerId_fk", referencedColumnName = "id", nullable = false)
    private User user;
    @Column(nullable = false)
    private Integer dice1;

    @Column(nullable = false)
    private Integer dice2;
    private Date rolledAt;
}

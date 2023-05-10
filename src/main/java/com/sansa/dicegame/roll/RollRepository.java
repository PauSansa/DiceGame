package com.sansa.dicegame.roll;

import com.sansa.dicegame.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RollRepository  extends JpaRepository<Roll, Integer> {
    List<Roll> findAllByUser(User user);
    List<Roll> findByUserOrderByRolledAtDesc(User user);
    List<Roll> findTop10ByUserOrderByRolledAtDesc(User user);
    void deleteAllByUser(User user);


}

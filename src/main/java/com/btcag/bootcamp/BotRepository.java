package com.btcag.bootcamp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BotRepository extends JpaRepository<Bot, Integer> {
    Optional<Bot> findByName(String name);
}

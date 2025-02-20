package com.btcag.bootcamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/robot")
public class BotService {

    @Autowired
    private BotRepository botRepository;

    // Alle Roboter
    @GetMapping
    public List<Bot> getBots() {
        return botRepository.findAll();
    }

    // 1x Roboter
    @GetMapping("/{name}")
    public ResponseEntity<Bot> getBot(@PathVariable("name") String name) {
        Optional<Bot> bot = botRepository.findByName(name);
        return bot.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Bot erstellen
    @PostMapping
    public ResponseEntity<Bot> createBot(@RequestBody Bot newBot) {
        // Überprüfen, ob ein Bot mit dem gleichen Namen schon existiert
        Optional<Bot> existingBot = botRepository.findByName(newBot.getName());
        if (existingBot.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null); // 409 Conflict, Bot existiert bereits
        }
        botRepository.save(newBot);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBot); // 201 Created, Bot erfolgreich erstellt
    }

    // Bot löschen
    @DeleteMapping("/{name}/delete")
    public ResponseEntity<String> deleteBot(@PathVariable("name") String name) {
        Optional<Bot> botToRemove = botRepository.findByName(name);
        if (botToRemove.isPresent()) {
            botRepository.delete(botToRemove.get());
            return ResponseEntity.ok("Bot " + name + " removed.");
        }
        return ResponseEntity.status(404).body("Bot not found.");
    }

    // Namen aktualisieren
    @PutMapping("/{name}/update")
    public ResponseEntity<Bot> updateBot(@PathVariable("name") String name, @RequestBody Bot newBot) {
        Optional<Bot> botToUpdate = botRepository.findByName(name);
        if (botToUpdate.isPresent()) {
            Bot bot = botToUpdate.get();
            bot.setName(newBot.getName());
            bot.setHealth(newBot.getHealth());
            bot.setAttackDamage(newBot.getAttackDamage());
            bot.setAttackRange(newBot.getAttackRange());
            bot.setMovementRate(newBot.getMovementRate());
            botRepository.save(bot);
            return ResponseEntity.ok(bot);
        }
        return ResponseEntity.status(404).body(null);
    }
}

package com.btcag.bootcamp;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@RestController
@RequestMapping("/robot")
public class BotService {

    // Bot erstellen
    @PostMapping(value = "/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bot> createBot(@RequestBody Bot newBot) {
        Bot bot = new Bot();
        Session newSession = Connection.getSession().openSession();
        newSession.beginTransaction();
        newSession.save(newBot);
        newSession.getTransaction().commit();
        newSession.close();
        return null;
    }

    // Bot löschen
    @DeleteMapping("/{name}/delete")
    public ResponseEntity<String> deleteBot(@PathVariable("name") String name) {
        return ResponseEntity.status(404).body("Bot not found.");
    }

    public static Bot getStatsForBot(){
        Scanner input = new Scanner(System.in);
        Bot newRobot = new Bot();
        System.out.println("Name: ");
        newRobot.setName(input.nextLine());
        System.out.println("AD: ");
        newRobot.setAttackDamage(input.nextBigDecimal());
        System.out.println("HP: ");
        newRobot.setHealth(input.nextBigDecimal());
        System.out.println("MovementRate: ");
        newRobot.setMovementRate(input.nextBigDecimal());
        System.out.println("Range: ");
        newRobot.setAttackRange(input.nextBigDecimal());

        return newRobot;
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Bot>> getAllBots() {
        Session session = Connection.getSession().openSession();
        List<Bot> bots = session.createQuery("FROM Bot", Bot.class).list();
        session.close();
        return ResponseEntity.ok(bots);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bot> getBot(@PathVariable("id") String id) {
        Session session = Connection.getSession().openSession();
        Bot bot = null;
        try {
            bot = session.get(Bot.class, id);
            if (bot == null) {
                System.out.println("Bot not found: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } finally {
            session.close();
        }
        return ResponseEntity.ok(bot);
    }

}

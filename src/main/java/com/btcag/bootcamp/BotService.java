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
    @PostMapping(value = "robot/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bot> createBot(@RequestBody Bot newBot) {
        Bot bot = new Bot();
        Session newSession = Connection.getSession().openSession();
        newSession.beginTransaction();
        newSession.save(newBot);
        newSession.getTransaction().commit();
        newSession.close();
        return null;
    }

    @PostMapping()
    public static void createRobot() throws IOException {
        Bot newBot = getStatsForBot();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(newBot);


        URL url = new URL("http://localhost:8080/create");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = json.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int statusCode = connection.getResponseCode();
        System.out.println("Response Code: " + statusCode);
    }

    // Bot löschen
    @DeleteMapping("/{name}/delete")
    public ResponseEntity<String> deleteBot(@PathVariable("name") String name) {
        return ResponseEntity.status(404).body("Bot not found.");
    }

    // Namen aktualisieren
    @PutMapping("/{name}/update")
    public ResponseEntity<Bot> updateBot(@PathVariable("name") String name, @RequestBody Bot newBot) {
        return ResponseEntity.status(404).body(null);
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
}

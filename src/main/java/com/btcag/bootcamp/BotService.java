package com.btcag.bootcamp;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/robot")
public class BotService {

    private RequestsGenerics<Bot> botRequests = new RequestsGenerics<>();

    public BotService() {
        botRequests.createItem(new Bot("eris", 100, 20, 5, 10));
        botRequests.createItem(new Bot("atlas", 150, 25, 6, 12));
    }

    @GetMapping
    public List<Bot> getBots() {
        return botRequests.getItems();
    }

    @GetMapping("/{name}")
    public Bot getBot(@PathVariable("name") String name) {
        for (Bot bot : botRequests.getItems()) {
            if (bot.getName().equals(name)) {
                return bot;
            }
        }
        return null; // Or return a 404 response
    }

    @PostMapping
    public Bot createBot(@RequestBody Bot newBot) {
        botRequests.createItem(newBot);
        return newBot;
    }

    @DeleteMapping("/{name}/delete")
    public String deleteBot(@PathVariable("name") String name) {
        Bot botToRemove = null;
        for (Bot bot : botRequests.getItems()) {
            if (bot.getName().equals(name)) {
                botToRemove = bot;
                break;
            }
        }
        if (botToRemove != null) {
            botRequests.removeItem(botToRemove);
            return "Bot " + name + " removed.";
        }
        return "Bot not found.";
    }
}

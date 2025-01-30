package com.btcag.bootcamp;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/robot")
public class BotService {

    private RequestsGenerics<Bot> bot = new RequestsGenerics<>();

    public BotService(){
        bot.createABot(new Bot("eris", 1, 1, 1, 1));
    }

    @PostMapping
    public void createBot(@RequestBody Bot bot){
        this.bot.createABot(bot);
    }

    @GetMapping
    public List<Bot> getBots(){
        return this.bot.getBots();
    }

}
package com.btcag.bootcamp;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/maps")
public class MapsService {

    @GetMapping
    public List<Map<String, String>> getMaps() {
        // Example data, you can replace with actual logic or database
        return List.of(
                new HashMap<String, String>() {{
                    put("id", "1");
                    put("name", "World Map");
                }},
                new HashMap<String, String>() {{
                    put("id", "2");
                    put("name", "Treasure Map");
                }}
        );
    }

    @GetMapping("/map/{id}")
    public Map<String, String> getMap(@PathVariable("id") String id) {
        Map<String, String> map = new HashMap<>();
        if ("1".equals(id)) {
            map.put("id", "1");
            map.put("name", "World Map");
        } else {
            map.put("id", "not found");
            map.put("name", "Map not found");
        }
        return map;
    }
}

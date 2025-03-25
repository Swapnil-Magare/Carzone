package com.carzone.ai;

import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/ai")
public class AIController {

    @Autowired
    private OllamaChatModel client;

    @GetMapping("/prompt/{message}")
    public Flux<String> prompt(@PathVariable String message) {
        Flux<String> response = client.stream(message);
        return response;
    }
}

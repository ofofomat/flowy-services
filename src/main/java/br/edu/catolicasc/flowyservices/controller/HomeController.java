package br.edu.catolicasc.flowyservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private static final String WELCOME_MESSAGE = "Welcome to the homepage!";

    @GetMapping("/")
    public String home() {
        return WELCOME_MESSAGE;
    }
}
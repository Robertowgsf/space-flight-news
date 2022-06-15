package com.robertowgsf.spaceflightnews.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public String getWelcomeMessage() {
        return "Back-end Challenge 2021 \uD83C\uDFC5 - Space Flight News";
    }
}

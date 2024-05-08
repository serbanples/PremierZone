package com.serbanples.backend.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.serbanples.backend.services.WebScrapingService;

import java.io.IOException;

@RestController
public class ScrapingController {

    private WebScrapingService webScrapingService;

    public ScrapingController(WebScrapingService webScrapingService) {
        this.webScrapingService = webScrapingService;
    }

    @PostMapping(path = "/scrape")
    public void scrapeData() throws IOException, InterruptedException {
        webScrapingService.scrapeData();
    }
}

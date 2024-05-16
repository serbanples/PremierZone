package com.serbanples.backend.controllers;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.serbanples.backend.services.WebScrapingService;

@RestController
@RequestMapping(path = "/api/scrape")
public class ScrapingController {

    private WebScrapingService webScrapingService;

    public ScrapingController(WebScrapingService webScrapingService) {
        this.webScrapingService = webScrapingService;
    }

    @GetMapping
    public String scrapePremierLeagueStats() throws IOException, InterruptedException {
        webScrapingService.scrapeData();
        return "Scraping process initiated. Check the server logs for progress.";
    }

}

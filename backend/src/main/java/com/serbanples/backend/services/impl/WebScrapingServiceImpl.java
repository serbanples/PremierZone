package com.serbanples.backend.services.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serbanples.backend.domain.dto.PlayerDto;
import com.serbanples.backend.domain.entities.PlayerEntity;
import com.serbanples.backend.mappers.Mapper;
import com.serbanples.backend.services.PlayerService;
import com.serbanples.backend.services.WebScrapingService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class WebScrapingServiceImpl implements WebScrapingService {
    
    @Autowired
    private PlayerService playerService;

    @Autowired
    private Mapper<PlayerEntity, PlayerDto> playerMapper;

    @Override
    public void scrapeData() throws IOException, InterruptedException {
        try {
            Document doc = Jsoup.connect("https://fbref.com/en/comps/9/Premier-League-Stats").get();
            Elements tables = doc.select("table.stats_table");
            Element table = tables.get(0);

            Elements links = table.select("a[href]");
            List<String> teamUrls = new ArrayList<>();

            for (Element link : links) {
                String url = link.attr("href");
                if (url.contains("/squads/")) {
                    teamUrls.add("https://fbref.com" + url);
                }
            }

            for (String teamUrl : teamUrls) {
                String teamName = teamUrl.substring(teamUrl.lastIndexOf("/") + 1, teamUrl.lastIndexOf("-Stats"));
                Document teamDoc = Jsoup.connect(teamUrl).get();
                Element statsTable = teamDoc.selectFirst("table.stats_table");

                if (statsTable != null) {
                    Elements rows = statsTable.select("tr");

                    for (int i = 2; i < rows.size() - 2; i++) {
                        Element row = rows.get(i);
                        Elements cols = row.select("th,td");
                        if (cols.size() >= 4) { 
                            PlayerDto playerDto = new PlayerDto();
                            playerDto.setName(cols.get(0).text());
                            playerDto.setNation(cols.get(1).text());
                            playerDto.setPosition(cols.get(2).text());
                            playerDto.setTeam(teamName);
                            PlayerEntity playerEntity = playerMapper.mapFrom(playerDto);
                            playerService.save(playerEntity);
                        }
                    }
                }
                Thread.sleep(5000);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

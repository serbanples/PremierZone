package com.serbanples.backend.services.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.serbanples.backend.services.WebScrapingService;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class WebScrapingServiceImpl implements WebScrapingService {

    @Override
    public void scrapeData() throws IOException, InterruptedException {
        List<String> allTeams = new ArrayList<>();

        // Fetch HTML content
        String url = "https://fbref.com/en/comps/9/Premier-League-Stats";
        Document doc = Jsoup.connect(url).get();
        Element table = doc.selectFirst("table.stats_table"); // Select the first table

        // Extract squad URLs
        Elements links = table.select("a[href^='/squads/']");
        for (Element link : links) {
            String teamUrl = "https://fbref.com" + link.attr("href");
            allTeams.add(teamUrl);
        }

        List<String> teamDataList = new ArrayList<>();

        for (String teamUrl : allTeams) {
            Document teamDoc = Jsoup.connect(teamUrl).get();
            Element stats = teamDoc.selectFirst("table.stats_table");

            if (stats != null) {
                String teamName = teamUrl.substring(teamUrl.lastIndexOf("/") + 1).replace("-Stats", "");
                String htmlStr = stats.outerHtml();

                // Format the data as needed
                // For example: Droplevel, etc.

                // Append team name to the data
                teamDataList.add(htmlStr + "," + teamName);
            }

            // Delay between requests to avoid being blocked
            TimeUnit.SECONDS.sleep(5);
        }

        // Write data to a CSV file or store it in the database
        writeToCsv(teamDataList);
    }

    private void writeToCsv(List<String> teamDataList) throws IOException {
        try (FileWriter writer = new FileWriter("./backend/src/main/java/com/serbanples/backend/services/stats.csv")) {
            for (String teamData : teamDataList) {
                writer.write(teamData + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

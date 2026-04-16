package com.example.Lottery.Service;

import com.example.Lottery.entity.LotteryResults;
import com.example.Lottery.repo.LotteryRepo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class LotteryService {
    @Autowired
    private LotteryRepo lotteryRepo;

    public void processAssignment() {
        String url = "https://www.lankayp.com/dlb/result/jayoda";

        try {
            System.out.println("Connecting to: " + url);
            Document doc = Jsoup.connect(url).timeout(10000).get();

            // Select the rows in the results table
            Elements rows = doc.select("table tr");

            for (Element row : rows) {
                Elements cells = row.select("td");

                // Ensure the row has the expected number of columns (Date, Game, Numbers, Jackpot, Draw No)
                if (cells.size() >= 5) {
                    String drawDate = cells.get(0).text();
                    String nextJackpot = cells.get(3).text();
                    String drawNoStr = cells.get(4).text();

                    // Extract Winning Letter and Numbers from the span/divs
                    Element winningCell = cells.get(2);
                    String letter = winningCell.select("div.lotto_no_r.lotto_no_hot").text();
                    String numbers = winningCell.select("div.lotto_no_r:not(.lotto_no_hot)")
                            .stream()
                            .map(Element::text)
                            .collect(Collectors.joining("-"));

                    String fullResult = letter + " " + numbers;

                    // Create Entity
                    LotteryResults entity = new LotteryResults();

                    // Convert Draw No string to int for the primary key
                    try {
                        entity.setEventNo(Integer.parseInt(drawNoStr.trim()));
                    } catch (NumberFormatException e) {
                        continue; // Skip header rows or invalid numbers
                    }

                    entity.setResults(fullResult);
                    entity.setDraw_date(drawDate);
                    entity.setNext_jackpot(nextJackpot);

                    // Save to database
                    lotteryRepo.save(entity);

                    System.out.println("Saved Event: " + drawNoStr + " | Results: " + fullResult);

                }
            }

        } catch (Exception e) {
            System.err.println("Scraping failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

}

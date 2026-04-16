package com.example.Lottery;

import com.example.Lottery.Service.LotteryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LotteryApplication {

	public static void main(String[] args) {

        SpringApplication.run(LotteryApplication.class, args);
	}

    @Bean
    public CommandLineRunner run(LotteryService lotteryService) {
        return args -> {
            System.out.println("Application Started... Executing Scraper...");
            lotteryService.processAssignment();
            System.out.println("Assignment Execution Finished.");
        };
    }

}

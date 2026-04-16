package com.example.Lottery.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table (name = "lotteryresult")
public class LotteryResults {

    @Id
    private int eventNo;
    private String next_jackpot;
    private String draw_date;
    private String results;

}

package com.example.Lottery.repo;

import com.example.Lottery.entity.LotteryResults;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotteryRepo extends JpaRepository<LotteryResults,Integer> {

}

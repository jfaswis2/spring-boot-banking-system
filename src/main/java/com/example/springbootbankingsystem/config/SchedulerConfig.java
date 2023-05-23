package com.example.springbootbankingsystem.config;

import com.example.springbootbankingsystem.service.impl.accountimpl.AccountServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class SchedulerConfig {
    private final AccountServiceImpl accountService;
/*
    @Scheduled(cron = "0 0 0 * * *")
    public void chargeMonthlyFee() {
        accountService.chargeMonthlyFee();
    }*/

}

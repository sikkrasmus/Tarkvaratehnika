package com.portfolio.backend.scheduling;

import com.portfolio.backend.service.PortfolioService;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    @Autowired
    PortfolioService portfolioService;


    @Scheduled(cron = "0 0 0,6,12,18 * * *")
//    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() throws IOException, JSONException {
        portfolioService.saveCurrentPriceForAllPortfolios();
    }
}

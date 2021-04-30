package com.buildit.controller;


import com.buildit.config.BuilditCrawlerConfig;
import com.buildit.config.properties.BuilditCrawlerConfigProperties;
import com.buildit.crawler.BuilditCrawler;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequiredArgsConstructor
@EnableConfigurationProperties(BuilditCrawlerConfigProperties.class)
public class CrawlerController {

    private final CrawlConfig crawlConfig;

    private final BuilditCrawlerConfigProperties builditCrawlerConfigProperties;

    private final BuilditCrawler builditCrawler;

    @GetMapping("/crawl")
    public void crawl(@RequestParam String website) throws Exception {
        PageFetcher pageFetcher = new PageFetcher(crawlConfig);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer= new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(crawlConfig, pageFetcher, robotstxtServer);

        controller.addSeed(website);

//        CrawlController.WebCrawlerFactory<BuilditCrawler> factory = BuilditCrawler::new;

        controller.start(BuilditCrawler.class, builditCrawlerConfigProperties.getNumberOfCrawlers());
    }

    @GetMapping("/getCrawlData")
    public Map<String, String> getCrawlData(@RequestParam String website) {


        return BuilditCrawlerConfig.CRAWL_STORAGE.get(website);
    }
}

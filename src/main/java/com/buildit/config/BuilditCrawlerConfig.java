package com.buildit.config;


import com.buildit.config.properties.BuilditCrawlerConfigProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
@EnableConfigurationProperties(BuilditCrawlerConfigProperties.class)
@RequiredArgsConstructor
public class BuilditCrawlerConfig {

    private final BuilditCrawlerConfigProperties builditCrawlerConfigProperties;

    public static final ConcurrentHashMap<String, Map<String, String>> CRAWL_STORAGE = new ConcurrentHashMap<>();

    @Bean
    public CrawlConfig crawlConfig() {
        File crawlStorage = new File(builditCrawlerConfigProperties.getCrawlInfoLocation());

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorage.getAbsolutePath());
        config.setMaxDepthOfCrawling(builditCrawlerConfigProperties.getMaxDepthOfCrawling());
        config.setMaxPagesToFetch(builditCrawlerConfigProperties.getMaxPagesToFetch());

        return config;
    }

}

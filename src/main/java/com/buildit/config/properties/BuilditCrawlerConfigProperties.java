package com.buildit.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("buildit")
@Data
public class BuilditCrawlerConfigProperties {

    private String crawlInfoLocation = "src/test/resources/crawler4j";

    private Integer maxDepthOfCrawling = 2;

    private Integer maxPagesToFetch = 10;

    private Integer numberOfCrawlers = 2;

}

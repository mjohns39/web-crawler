package com.buildit.crawler;

import com.buildit.config.BuilditCrawlerConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

@Component
public class BuilditCrawler extends WebCrawler {


    private final ObjectMapper mapper = new ObjectMapper();

    private final static Pattern EXCLUSIONS = Pattern.compile(".*(\\.(css|js|xml|gif|jpg|png|mp3|mp4|zip|gz|pdf))$");



    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String urlString = url.getURL().toLowerCase();
        return !EXCLUSIONS.matcher(urlString).matches()
//                && url.getDomain().matches(referringPage.getWebURL().getDomain())
//                && urlString.startsWith("https://www.baeldung.com/")
                ;
    }

    @Override
    public void visit(Page page) {

        String url = page.getWebURL().getURL();

        Map<String, String> crawlData;
        if(BuilditCrawlerConfig.CRAWL_STORAGE.containsKey(url)) {
            crawlData = BuilditCrawlerConfig.CRAWL_STORAGE.get(url);
        } else {
            crawlData = new HashMap<>();
        }

        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String title = htmlParseData.getTitle();
            String text = htmlParseData.getText();
            String html = htmlParseData.getHtml();
            Set<WebURL> links = htmlParseData.getOutgoingUrls();

            // do something with the collected data

            crawlData.put("title", title);
            crawlData.put("text", text);
            crawlData.put("HTML", html);

            BuilditCrawlerConfig.CRAWL_STORAGE.put(url, crawlData);
        }
    }
}

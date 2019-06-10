package com.aravindhu.webcrawler.webcrawler.model.domain.service.interfaces;

import com.aravindhu.webcrawler.webcrawler.model.response.SitemapResponse;
import org.springframework.stereotype.Service;

import java.net.URL;

public interface WebCrawlerService {

    SitemapResponse crawl(String url);

}

package com.aravindhu.webcrawler.webcrawler.rest;

import com.aravindhu.webcrawler.webcrawler.model.domain.service.interfaces.WebCrawlerService;
import com.aravindhu.webcrawler.webcrawler.model.response.ErrorResponse;
import com.aravindhu.webcrawler.webcrawler.model.response.SitemapResponse;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ResourceConstants.SITEMAP_CRAWLER_V1)
public class SitemapResource {

    @Autowired
    private WebCrawlerService webCrawlerService;

    @RequestMapping(path ="", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public SitemapResponse getSitemap(
            @RequestParam(value = "url")
                    String url
    ) {
        return webCrawlerService.crawl(url);
    }

}

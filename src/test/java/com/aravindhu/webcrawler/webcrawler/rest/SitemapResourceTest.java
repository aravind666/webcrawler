package com.aravindhu.webcrawler.webcrawler.rest;

import com.aravindhu.webcrawler.webcrawler.model.domain.service.interfaces.WebCrawlerService;
import com.aravindhu.webcrawler.webcrawler.model.response.SitemapResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.runner.RunWith;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(SitemapResource.class)
class SitemapResourceTest {
    @MockBean
    private WebCrawlerService webCrawlerService;
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void getSitemap() throws Exception {
        SitemapResponse mockSiteMapResponse = new SitemapResponse();
        mockSiteMapResponse.setNumberOfImages(23);
        mockSiteMapResponse.setDomain("http://aravindhu.com");
        given(webCrawlerService.crawl("aravindhu.com")).willReturn(mockSiteMapResponse);
        this.mockMvc.perform(get("/crawler/sitemap/v1?url=aravindhu.com")).andExpect(status().isOk()).andExpect(content().string(containsString("http://aravindhu.com")));
    }
}

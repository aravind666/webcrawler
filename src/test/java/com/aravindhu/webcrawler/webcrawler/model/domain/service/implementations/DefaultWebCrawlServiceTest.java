package com.aravindhu.webcrawler.webcrawler.model.domain.service.implementations;

import com.aravindhu.webcrawler.webcrawler.model.domain.service.interfaces.WebCrawlerService;
import com.aravindhu.webcrawler.webcrawler.model.response.SitemapResponse;
import com.aravindhu.webcrawler.webcrawler.rest.SitemapResource;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(WebCrawlerService.class)
class DefaultWebCrawlServiceTest {

    @MockBean
    private WebCrawlerService webCrawlerService;
    private SitemapResponse mockSiteMapResponse;

    @BeforeEach
    void setUp() {

        setupMockedSiteMapResponse();

        given(webCrawlerService.crawl("aravindhu.com")).willReturn(mockSiteMapResponse);

        mockSiteMapResponse = webCrawlerService.crawl("aravindhu.com");
    }

    @Test
    void shouldAddprotocolToDomainIfnotExists() throws Exception {
        Assert.assertEquals("http://aravindhu.com", mockSiteMapResponse.getDomain());
    }

    @Test
    void siteMapShouldProvideCountOfImages() throws Exception {
        Assert.assertTrue(mockSiteMapResponse.getNumberOfImages() > 1);
    }

    @Test
    void siteMapShouldProvideListOfInternalLinks() throws Exception {
        Assert.assertTrue(mockSiteMapResponse.getInternalLinks().size() > 1);
    }

    @Test
    void siteMapShouldProvideListOfExternalLinks() throws Exception {
        Assert.assertTrue(mockSiteMapResponse.getExternalLinks().size() > 1);
    }

    @Test
    void siteMapShouldProvideListOfBrokenLinks() throws Exception {
        Assert.assertTrue(mockSiteMapResponse.getDeadLinks().size() > 1);
    }


    private void setupMockedSiteMapResponse() {
        mockSiteMapResponse = new SitemapResponse();
        mockSiteMapResponse.setDomain("http://aravindhu.com");
        mockSiteMapResponse.setNumberOfImages(23);
        HashSet<String> internalLinks;
        HashSet<String> externalLinks;
        HashMap<String, HashSet<String>> pageResources = new HashMap<String, HashSet<String>>();
        HashSet<String> brokenLinks = new HashSet<String>();

        String[] arrayOfExtLinks = { "http://www.mooreslaw.org/",
                "http://en.wikipedia.org/wiki/Bhagavad_Gita",
                "https://developer.mozilla.org/en-US/docs/Web/API/FileList",
                "http://www.w3.org/TR/WCAG20/#understandable",
                "https://www.linkedin.com/company/aconex/" };

        String[] arrayOfIntLinks = {
                "http://www.aravindhu.com/2014/08/06/web-accessibility/#",
                "http://www.aravindhu.com/html5/2014/08/06/manipulate-browser-history-with-html5/",
                "http://www.aravindhu.com/git/2014/07/22/git-commands-used-quite-often/",
                "http://www.aravindhu.com/oops/2014/08/23/object-oriented-design-principles/#"
        };

        String[] arrayOfBrokenLinks = {
                "http://www.aravindhu.com/mytimeline/#",
                "http://www.aravindhu.com/bigdata/2018/06/08/360-degree-restrospections/",
                "http://www.aravindhu.com/general/2015/05/17/engineering-software-quality/#"
        };

        String[] arrayOfArtifacts = {
                "https://camo.githubusercontent.com/121cd7cbdc3e4855075ea8b558508b91ac463ac2/68747470733a2f2f73332e616d617a6f6e6177732e636f6d2f6769746875622f726962626f6e732f666f726b6d655f6c6566745f677265656e5f3030373230302e706e67",
                "http://www.aravindhu.com/img/aravind.jpeg"
        };

        internalLinks = new HashSet<String>(Arrays.asList(arrayOfIntLinks));
        externalLinks = new HashSet<String>(Arrays.asList(arrayOfExtLinks));
        brokenLinks = new HashSet<String>(Arrays.asList(arrayOfBrokenLinks));
        pageResources.put("http://www.aravindhu.com/javascript/2014/08/02/angular-js-terminologies-for-starters/", new HashSet<String>(Arrays.asList(arrayOfArtifacts)));

        mockSiteMapResponse.setPageArtifacts(pageResources);
        mockSiteMapResponse.setInternalLinks(internalLinks);
        mockSiteMapResponse.setExternalLinks(externalLinks);
        mockSiteMapResponse.setDeadLinks(brokenLinks);
    }


}
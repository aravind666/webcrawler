package com.aravindhu.webcrawler.webcrawler.model.domain.service.implementations;

import com.aravindhu.webcrawler.webcrawler.model.domain.service.interfaces.WebCrawlerService;
import com.aravindhu.webcrawler.webcrawler.model.response.SitemapResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;

@Service
public class DefaultWebCrawlService implements WebCrawlerService {

    private HashSet<String> internalLinks;
    private HashSet<String> externalLinks;
    private HashMap<String, HashSet<String>> pageResources;
    private HashSet<String> brokenLinks;
    private SitemapResponse sitemapResponse;
    private URL domainToCrawl;
    private int numberOfimages;

    private void setupLinkSets() {
        internalLinks = new HashSet<String>();
        externalLinks = new HashSet<String>();
        brokenLinks = new HashSet<String>();
        pageResources = new HashMap<String, HashSet<String>>();
    }

    public SitemapResponse crawl(String url) {
        this.setupLinkSets();
        if (!url.startsWith("http://")) {
            url = "http://" + url;
        }

        URL crawlUrl = null;
        try {
            crawlUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        this.domainToCrawl = crawlUrl;

        this.gatherLinksOfUrl(this.domainToCrawl);
        return prepareSitemapResponse(url);
    }

    private Document connectToUrl(String url) throws IOException {
        return Jsoup.connect(url).get();
    }

    private void gatherLinksOfUrl(URL url) {
        if (!internalLinks.contains(url.toString()) && isLinkInDomain(url)) {
            HashSet<String> currentPageImages = new HashSet<String>();
            try {
                String stringUrl = url.toString();
                internalLinks.add(stringUrl);
                Document document = connectToUrl(stringUrl);
                Elements imgSources = document.select("img");
                for (Element img : imgSources) {
                    currentPageImages.add(img.attr("abs:src"));
                    numberOfimages++;
                }
                pageResources.put(stringUrl, currentPageImages);
                Elements currentLinks = document.select("a[href]");
                for (Element page : currentLinks) {
                    if (isLinkInDomain(new URL(page.baseUri()))) {
                        gatherLinksOfUrl(new URL(page.attr("abs:href")));
                    }
                }
            } catch (IOException e) {
                brokenLinks.add(url.toString());
            }
        } else if (!isLinkInDomain(url)) {
            externalLinks.add(url.toString());
        }
    }

    private boolean isLinkInDomain(URL url) {
        return url.getHost().contains(this.domainToCrawl.getHost());
    }

    private SitemapResponse prepareSitemapResponse(String url) {
        this.sitemapResponse = new SitemapResponse();
        this.sitemapResponse.setDomain(url);
        this.sitemapResponse.setDeadLinks(brokenLinks);
        this.sitemapResponse.setNumberOfImages(numberOfimages);
        this.sitemapResponse.setInternalLinks(internalLinks);
        this.sitemapResponse.setExternalLinks(externalLinks);
        this.sitemapResponse.setPageArtifacts(pageResources);
        return this.sitemapResponse;
    }

}

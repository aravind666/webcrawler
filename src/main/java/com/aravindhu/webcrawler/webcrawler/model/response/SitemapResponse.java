package com.aravindhu.webcrawler.webcrawler.model.response;

import java.util.HashMap;
import java.util.HashSet;

public class SitemapResponse {
    private String domain;
    private HashSet<String> externalLinks;
    private HashSet<String> internalLinks;
    private HashSet<String> deadLinks;
    private HashMap<String, HashSet<String>> pageArtifacts;
    private int numberOfImages;
    public SitemapResponse() {
        super();
    }

    public SitemapResponse(String domain, HashSet<String> externalLinks, HashSet<String> internalLinks, HashSet<String> deadLinks, HashMap<String, HashSet<String>> pageArtifacts, int numberOfImages) {
        this.domain = domain;
        this.externalLinks = externalLinks;
        this.internalLinks = internalLinks;
        this.deadLinks = deadLinks;
        this.pageArtifacts = pageArtifacts;
        this.numberOfImages = numberOfImages;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public HashSet<String> getExternalLinks() {
        return externalLinks;
    }

    public void setExternalLinks(HashSet<String> externalLinks) {
        this.externalLinks = externalLinks;
    }

    public HashSet<String> getInternalLinks() {
        return internalLinks;
    }

    public void setInternalLinks(HashSet<String> internalLinks) {
        this.internalLinks = internalLinks;
    }

    public HashSet<String> getDeadLinks() {
        return deadLinks;
    }

    public void setDeadLinks(HashSet<String> deadLinks) {
        this.deadLinks = deadLinks;
    }

    public HashMap<String, HashSet<String>> getPageArtifacts() {
        return pageArtifacts;
    }

    public void setPageArtifacts(HashMap<String, HashSet<String>> pageArtifacts) {
        this.pageArtifacts = pageArtifacts;
    }

    public int getNumberOfImages() {
        return numberOfImages;
    }

    public void setNumberOfImages(int numberOfImages) {
        this.numberOfImages = numberOfImages;
    }

}

package com.google.adwords;

public class GoogleKeywordBuilder {
    private String text;
    private int id;
    private int campaignId;
    private GoogleStatus status;
    private int bid;

    public GoogleKeywordBuilder withText(String text) {
        this.text = text;
        return this;
    }

    public GoogleKeywordBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public GoogleKeywordBuilder withBid(int bid) {
        this.bid = bid;
        return this;
    }

    public GoogleKeywordBuilder withCampaignId(int campaignId) {
        this.campaignId = campaignId;
        return this;
    }

    public GoogleKeywordBuilder withStatus(GoogleStatus status) {
        this.status = status;
        return this;
    }

    public GoogleKeyword build() {
        return new GoogleKeyword(text, id, campaignId, status, bid);
    }
}
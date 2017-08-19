package com.google.adwords;

public class GoogleKeyword {

    public String text;
    public int id;
    public int campaignId;
    public GoogleStatus status;
    public int bid;

    public GoogleKeyword(String text, int id, int campaignId, GoogleStatus status, int bid) {
        this.text = text;
        this.id = id;
        this.campaignId = campaignId;
        this.status = status;
        this.bid = bid;
    }

    public String getText() {
        return text;
    }

    public int getId() {
        return id;
    }

    public int getCampaignId() {
        return campaignId;
    }

    public GoogleStatus getStatus() {
        return status;
    }

    public int getBid() {
        return bid;
    }

}

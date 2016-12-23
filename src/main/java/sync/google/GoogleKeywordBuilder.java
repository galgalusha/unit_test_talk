package sync.google;

public class GoogleKeywordBuilder {
    private String text;
    private int id;
    private int campaignId;
    private String status;

    public GoogleKeywordBuilder withText(String text) {
        this.text = text;
        return this;
    }

    public GoogleKeywordBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public GoogleKeywordBuilder withCampaignId(int campaignId) {
        this.campaignId = campaignId;
        return this;
    }

    public GoogleKeywordBuilder withStatus(String status) {
        this.status = status;
        return this;
    }

    public GoogleKeyword build() {
        return new GoogleKeyword(text, id, campaignId, status);
    }
}
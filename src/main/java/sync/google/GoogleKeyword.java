package sync.google;

public class GoogleKeyword {

    public String text;
    public int id;
    public int campaignId;
    public String status;

    public GoogleKeyword(String text, int id, int campaignId, String status) {
        this.text = text;
        this.id = id;
        this.campaignId = campaignId;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

}

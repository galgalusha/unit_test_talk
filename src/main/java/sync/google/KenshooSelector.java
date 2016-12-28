package sync.google;

import java.util.ArrayList;
import java.util.List;

public class KenshooSelector {

    private List<Integer> campaignIdInTargets = new ArrayList<Integer>();

    public KenshooSelector(List<Integer> campaignIdInTargets) {
        this.campaignIdInTargets = campaignIdInTargets;
    }

    public List<Integer> getCampaignIdInTargets() {
        return campaignIdInTargets;
    }

    public void setCampaignIdInTargets(List<Integer> campaignIdInTargets) {
        this.campaignIdInTargets = campaignIdInTargets;
    }
}

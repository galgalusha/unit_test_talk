package sync.google;

import com.google.adwords.GoogleKeyword;
import com.google.adwords.GoogleKeywordService;
import com.google.adwords.GoogleSelector;
import com.google.adwords.GoogleStatus;

import java.util.List;
import static org.jooq.lambda.Seq.seq;


public class GoogleKeywordDownloader {

    private final GoogleKeywordService googleApi;

    public GoogleKeywordDownloader(GoogleKeywordService googleApi) {
        this.googleApi = googleApi;
    }

    public List<KenshooKeyword> download(CampaignPredicate predicate) {
        return seq(googleApi.getKeywords(selectingCampaigns(predicate.campaignIdInTargets)))
                .map(this::fromGoogleKeyword)
                .toList();
    }

    private KenshooKeyword fromGoogleKeyword(GoogleKeyword googleKeyword) {
        KenshooKeyword kenshooKeyword = new KenshooKeyword();
        kenshooKeyword.idInTarget = googleKeyword.getId();
        kenshooKeyword.status = fromGoogleStatus(googleKeyword.getStatus());
        kenshooKeyword.text = googleKeyword.getText();
        kenshooKeyword.bid = isActive(googleKeyword) ?fromMicroAmount(googleKeyword) : 0;
        return kenshooKeyword;
    }

    private float fromMicroAmount(GoogleKeyword googleKeyword) {
        return ((float) googleKeyword.getBid()) / 1000000;
    }

    private boolean isActive(GoogleKeyword kw) {
        return fromGoogleStatus(kw.getStatus()).equals(KenshooStatus.Active);
    }

    private GoogleSelector selectingCampaigns(List<Integer> ids) {
        GoogleSelector selector = new GoogleSelector();
        selector.campaignIds = ids;
        return selector;
    }

    private KenshooStatus fromGoogleStatus(GoogleStatus status) {
        switch (status) {
            case Active : return KenshooStatus.Active;
            case Paused : return KenshooStatus.NonActive;
            case Deleted: return KenshooStatus.NonActive;
            default: return KenshooStatus.Unknown;
        }
    }
}

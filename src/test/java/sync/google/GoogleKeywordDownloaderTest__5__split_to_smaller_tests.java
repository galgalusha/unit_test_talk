package sync.google;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static com.sun.javaws.JnlpxArgs.verify;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class GoogleKeywordDownloaderTest__5__split_to_smaller_tests {

    @Mock
    GoogleApi googleApi;

    GoogleKeywordDownloader testedDownloader;

    @Before
    public void setup() {
        testedDownloader = new GoogleKeywordDownloader(googleApi);
    }

    @Test
    public void testIdInTargetIsTakenFromIdInGoogle() {

        setupKeywordsInGoogle(
                googleKeyword().withCampaignId(1000).withId(123).build()
        );

        KenshooKeyword kenshooKeyword = testedDownloader.download(selectingCampaigns(1000)).get(0);

        assertThat(kenshooKeyword.idInTarget, equalTo(123));
    }

    @Test
    public void testActiveInGoogleBecomesActiveInKenshoo() {

        setupKeywordsInGoogle(
                googleKeyword().withCampaignId(1000).withStatus("Active").build()
        );

        KenshooKeyword kenshooKeyword = testedDownloader.download(selectingCampaigns(1000)).get(0);

        assertThat(kenshooKeyword.statusInTarget, equalTo("Active"));
    }

    @Test
    public void testUsingGoogleSelectorWithSameCampaignsAsKenshooSelector() {

        setupKeywordsInGoogle(
                googleKeyword().withCampaignId(1000).build()
        );

        testedDownloader.download(selectingCampaigns(1000));

        // verify(googleApi.getKeywords( ???????  <--------- whats in here ??? ));
    }

    private KenshooSelector selectingCampaigns(int campaignIdInTarget) {
        KenshooSelector selector = new KenshooSelector();
        selector.campaignIdInTargets.add(campaignIdInTarget);
        return selector;
    }

    private void setupKeywordsInGoogle(GoogleKeyword... keywords) {
        when(googleApi.getKeywords(any())).thenReturn(Arrays.asList(keywords));
    }

    private GoogleKeywordBuilder googleKeyword() {
        return new GoogleKeywordBuilder();
    }

}
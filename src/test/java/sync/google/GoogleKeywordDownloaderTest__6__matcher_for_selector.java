package sync.google;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GoogleKeywordDownloaderTest__6__matcher_for_selector {

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

        verify(googleApi.getKeywords(argThat(selectorWithCampaign(1000))));
    }

    private Matcher<GoogleSelector> selectorWithCampaign(int id) {
        return new ArgumentMatcher<GoogleSelector>() {
            @Override
            public boolean matches(Object arg) {
                return ((GoogleSelector)arg).campaignIds.get(0) == id &&
                        ((GoogleSelector)arg).campaignIds.size() == 1;
            }
        };
    }

    private KenshooSelector selectingCampaigns(int campaignIdInTarget) {
        KenshooSelector selector = new KenshooSelector();
        selector.campaignIdInTargets.add(campaignIdInTarget);
        return selector;
    }

    private void setupKeywordsInGoogle(GoogleKeyword... keywords) {
        //
        // TODO: how to make it smarter if we want to filter by given selector?
        //
        when(googleApi.getKeywords(any())).thenReturn(Arrays.asList(keywords));
    }

    private GoogleKeywordBuilder googleKeyword() {
        return new GoogleKeywordBuilder();
    }

}
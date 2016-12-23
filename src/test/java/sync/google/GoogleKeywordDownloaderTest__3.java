package sync.google;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GoogleKeywordDownloaderTest__3 {

    @Mock
    GoogleApi googleApi;

    GoogleKeywordDownloader testedDownloader;

    @Before
    public void setup() {
        testedDownloader = new GoogleKeywordDownloader(googleApi);
    }

    @Test
    public void testDownload() {

        /**
         * ------------------------------------------------------------------
         *  solution: table style setup
         *
         *  requires a builder
         * ------------------------------------------------------------------
         */
        setupKeywordsInGoogle(
                googleKeyword().withCampaignId(1000).withId(1).withText("shoes").withStatus("Active").build(),
                googleKeyword().withCampaignId(1000).withId(2).withText("shirt").withStatus("Paused").build()
        );

        //
        // new selector
        //
        KenshooSelector selector = new KenshooSelector();
        selector.campaignIdInTargets.add(1000);

        //
        // -----> Action!
        //
        List<KenshooKeyword> kenshooKeywords = testedDownloader.download(selector);

        assertThat(kenshooKeywords, hasSize(2));

        //
        // verify 1st keyword
        //
        assertThat(kenshooKeywords.get(0).idInTarget, equalTo(1));
        assertThat(kenshooKeywords.get(0).statusInTarget, equalTo("Active"));
        assertThat(kenshooKeywords.get(0).text, equalTo("shoes"));

        //
        // verify 2nd keyword
        //
        assertThat(kenshooKeywords.get(1).idInTarget, equalTo(2));
        assertThat(kenshooKeywords.get(1).statusInTarget, equalTo("Paused"));
        assertThat(kenshooKeywords.get(1).text, equalTo("shirts"));
    }

    private void setupKeywordsInGoogle(GoogleKeyword... keywords) {
        when(googleApi.getKeywords(any())).thenReturn(Arrays.asList(keywords));
    }

    private GoogleKeywordBuilder googleKeyword() {
        return new GoogleKeywordBuilder();
    }

}
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

public class GoogleKeywordDownloaderTest__2 {

    @Mock
    GoogleApi googleApi;

    GoogleKeywordDownloader testedDownloader;

    /**
     * ------------------------------------------------------------------
     *   bad solution:
     *
     *   1. hidden values
     *   2. is it reusable? (when campaign is 2000)
     * ------------------------------------------------------------------
     */
    final GoogleKeyword googleKeyword1_campaign_1000 = mockGoogleKeyword_1();
    final GoogleKeyword googleKeyword2_campaign_1000 = mockGoogleKeyword_2();

    @Before
    public void setup() {
        testedDownloader = new GoogleKeywordDownloader(googleApi);
    }

    @Test
    public void testDownload() {

        when(googleApi.getKeywords(any())).thenReturn(Arrays.asList(
                googleKeyword1_campaign_1000,
                googleKeyword2_campaign_1000));

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

    private GoogleKeyword mockGoogleKeyword_2() {
        GoogleKeyword googleKeyword2 = mock(GoogleKeyword.class);
        when(googleKeyword2.getId()).thenReturn(2);
        when(googleKeyword2.getCampaignId()).thenReturn(1000);
        when(googleKeyword2.getStatus()).thenReturn("Paused");
        when(googleKeyword2.getText()).thenReturn("shirts");
        return googleKeyword2;
    }

    private GoogleKeyword mockGoogleKeyword_1() {
        GoogleKeyword googleKeyword1 = mock(GoogleKeyword.class);
        when(googleKeyword1.getId()).thenReturn(1);
        when(googleKeyword1.getCampaignId()).thenReturn(1000);
        when(googleKeyword1.getStatus()).thenReturn("Active");
        when(googleKeyword1.getText()).thenReturn("shoes");
        return googleKeyword1;
    }

}
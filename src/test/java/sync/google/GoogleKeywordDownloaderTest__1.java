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

public class GoogleKeywordDownloaderTest__1 {

    @Mock
    GoogleApi googleApi;

    GoogleKeywordDownloader testedDownloader;

    @Before
    public void setup() {
        testedDownloader = new GoogleKeywordDownloader(googleApi);
    }

    @Test
    public void testDownload() {

        //
        // 1st google keyword
        //
        GoogleKeyword googleKeyword1 = mock(GoogleKeyword.class);
        when(googleKeyword1.getId()).thenReturn(1);
        when(googleKeyword1.getCampaignId()).thenReturn(1000);
        when(googleKeyword1.getStatus()).thenReturn("Active");
        when(googleKeyword1.getText()).thenReturn("shoes");

        //
        // 2st google keyword
        //
        GoogleKeyword googleKeyword2 = mock(GoogleKeyword.class);
        when(googleKeyword2.getId()).thenReturn(2);
        when(googleKeyword2.getCampaignId()).thenReturn(1000);
        when(googleKeyword2.getStatus()).thenReturn("Paused");
        when(googleKeyword2.getText()).thenReturn("shirts");

        when(googleApi.getKeywords(any())).thenReturn(Arrays.asList(googleKeyword1, googleKeyword2));

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

}
package sync.google;

import com.google.adwords.GoogleKeywordService;
import com.google.adwords.GoogleKeyword;
import com.google.adwords.GoogleSelector;
import com.google.adwords.GoogleStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GoogleKeywordDownloaderTest {

    @Mock
    GoogleKeywordService googleApi;

    GoogleKeywordDownloader testedDownloader;

    private final static double FLOAT_ERROR_RANGE = 0;

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
        when(googleKeyword1.getStatus()).thenReturn(GoogleStatus.Active);
        when(googleKeyword1.getText()).thenReturn("shoes");
        when(googleKeyword1.getBid()).thenReturn(500000);

        //
        // 2st google keyword
        //
        GoogleKeyword googleKeyword2 = mock(GoogleKeyword.class);
        when(googleKeyword2.getId()).thenReturn(2);
        when(googleKeyword2.getCampaignId()).thenReturn(1000);
        when(googleKeyword2.getStatus()).thenReturn(GoogleStatus.Deleted);
        when(googleKeyword2.getText()).thenReturn("shirts");
        when(googleKeyword2.getBid()).thenReturn(1000000);

        when(googleApi.getKeywords(any())).thenReturn(Arrays.asList(googleKeyword1, googleKeyword2));

        //
        // new selector
        //
        CampaignPredicate predicate = new CampaignPredicate();
        predicate.campaignIdInTargets.add(1000);

        //
        // -----> Action!
        //
        List<KenshooKeyword> kenshooKeywords = testedDownloader.download(predicate);

        assertThat(kenshooKeywords, hasSize(2));

        //
        // verify 1st keyword
        //
        assertThat(kenshooKeywords.get(0).idInTarget, equalTo(1));
        assertThat(kenshooKeywords.get(0).status, equalTo(KenshooStatus.Active));
        assertThat(kenshooKeywords.get(0).text, equalTo("shoes"));
        assertThat((double)kenshooKeywords.get(0).bid, closeTo(0.5, FLOAT_ERROR_RANGE));

        //
        // verify 2nd keyword
        //
        assertThat(kenshooKeywords.get(1).idInTarget, equalTo(2));
        assertThat(kenshooKeywords.get(1).status, equalTo(KenshooStatus.NonActive));
        assertThat(kenshooKeywords.get(1).text, equalTo("shirts"));
        assertThat("non-active keyword should be downloaded with bid=0", (double)kenshooKeywords.get(1).bid, closeTo(0, FLOAT_ERROR_RANGE));

        //
        // verify selector
        //
        ArgumentCaptor<GoogleSelector> captor = ArgumentCaptor.forClass(GoogleSelector.class);
        verify(googleApi).getKeywords(captor.capture());
        assertThat(captor.getValue().campaignIds, containsInAnyOrder(1000));
    }
}
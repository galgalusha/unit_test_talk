package sync.google;

import com.google.adwords.GoogleKeyword;
import com.google.adwords.GoogleKeywordService;
import com.google.adwords.GoogleStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class Exercise_1_Matchers {

    @Mock
    GoogleKeywordService googleKeywordService;

    private GoogleKeywordDownloader downloader;

    private CampaignPredicate predicate = new CampaignPredicate();

    @Mock
    private GoogleKeyword googleKeyword;

    @Before
    public void setup() {
        downloader = new GoogleKeywordDownloader(googleKeywordService);
        predicate.campaignIdInTargets.add(1000);
        when(googleKeyword.getId()).thenReturn(1);
        when(googleKeyword.getCampaignId()).thenReturn(1000);
        when(googleKeyword.getStatus()).thenReturn(GoogleStatus.Active);
        when(googleKeyword.getText()).thenReturn("shoes");
        when(googleKeyword.getBid()).thenReturn(500000);
        when(googleKeywordService.getKeywords(any())).thenReturn(Arrays.asList(googleKeyword));
    }

    @Test
    public void testThatFirstKeywordHasTextShoes() {

        List<KenshooKeyword> downloadedKeywords = downloader.download(predicate);

        KenshooKeyword firstKeyword = downloadedKeywords.get(0);

        // TODO:
        // assert that firstKeyword has text equal to "shoes"
    }

    @Test
    public void testThatSomeKeywordHasTextShoes() {

        List<KenshooKeyword> downloadedKeywords = downloader.download(predicate);


        // TODO:
        // assert that downloaded keywords has an item with text equal to "shoes"
    }

    @Test
    public void testThatGoogleServiceWasCalledWithSelectorThatHasCampaignId1000() {

        List<KenshooKeyword> downloadedKeywords = downloader.download(predicate);

        // TODO:
        // verify 'googleKeywordService.getKeywords' invoked with a selector that
        // has campaign ID '1000'
        // HINT: you need to use 'verify' with 'argThat' with 'Matcher'
    }

}

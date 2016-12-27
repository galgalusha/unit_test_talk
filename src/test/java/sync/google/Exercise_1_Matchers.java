package sync.google;

import com.google.adwords.GoogleKeywordService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class Exercise_1_Matchers {

    @Mock
    GoogleKeywordService googleKeywordService;

    private GoogleKeywordDownloader downloader;

    @Before
    public void setup() {
        downloader = new GoogleKeywordDownloader(googleKeywordService);
    }

    @Test
    public void testThatFirstKeywordHasTextShoes() {

        List<KenshooKeyword> downloadedKeywords = downloader.download(someSelector());

        KenshooKeyword firstKeyword = downloadedKeywords.get(0);

        // TODO:
        // assert that firstKeyword has text equal to "shoes"
    }

    @Test
    public void testThatSomeKeywordHasTextShoes() {

        List<KenshooKeyword> downloadedKeywords = downloader.download(someSelector());


        // TODO:
        // assert that downloaded keywords has an item with text equal to "shoes"
    }

    @Test
    public void testThatGoogleServiceWasCalledWithSelectorThatHasCampaignId1000() {

        List<KenshooKeyword> downloadedKeywords = downloader.download(someSelector());

        // TODO:
        // verify 'googleKeywordService.getKeywords' invoked with a selector that
        // has campaign ID '1000'
    }


    private KenshooSelector someSelector() {
        return null;
    }
}

package sync.google;

import com.google.adwords.GoogleKeywordService;
import com.google.adwords.GoogleSelector;
import com.google.adwords.KeywordMutateCommand;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.verify;

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
        assertThat(firstKeyword, hasText("shoes"));
    }

    @Test
    public void testThatSomeKeywordHasTextShoes() {

        List<KenshooKeyword> downloadedKeywords = downloader.download(someSelector());


        // TODO:
        // assert that downloaded keywords has an item with text equal to "shoes"
        assertThat(downloadedKeywords, hasItem(hasText("shoes")));
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

    private Matcher<KenshooKeyword> hasText(String text) {

        return new ArgumentMatcher<KenshooKeyword>() {

            public boolean matches(Object o) {
                return ((KenshooKeyword)o).text.equals(text);
            }
        };
    }



}

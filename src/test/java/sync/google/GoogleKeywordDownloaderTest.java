package sync.google;

import com.google.adwords.GoogleKeyword;
import com.google.adwords.GoogleKeywordBuilder;
import com.google.adwords.GoogleKeywordService;
import com.google.adwords.GoogleSelector;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GoogleKeywordDownloaderTest {

    @Mock
    GoogleKeywordService googleApi;

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

        //
        // verify selector
        //
        ArgumentCaptor<GoogleSelector> captor = ArgumentCaptor.forClass(GoogleSelector.class);
        verify(googleApi).getKeywords(captor.capture());
        assertThat(captor.getValue().campaignIds, containsInAnyOrder(1000));
    }

    @Test
    public void testDownloadTwoKeywordsVerifyTheFirst() {
        setupGoogleApi(
                withKeyword().withId(1).withCampaignId(1000).withStatus("Active").withText("shoes").build(),
                withKeyword().withId(2).withCampaignId(1000).withStatus("Paused").withText("shirts").build());

        List<KenshooKeyword> kenshooKeywords = testedDownloader.download(fromCampaignId(1000));

        assertThat(kenshooKeywords.get(0), isKeyword(withIdInTarget(1), withStatusInTarget("Active"), withText("shoes")));
    }

    @Test
    public void testDownloadTwoKeywordsVerifyTheSecond() {
        setupGoogleApi(
                withKeyword().withId(1).withCampaignId(1000).withStatus("Active").withText("shoes").build(),
                withKeyword().withId(2).withCampaignId(1000).withStatus("Paused").withText("shirts").build());

        List<KenshooKeyword> kenshooKeywords = testedDownloader.download(fromCampaignId(1000));

        assertThat(kenshooKeywords.get(1), isKeyword(withIdInTarget(2), withStatusInTarget("Paused"), withText("shirts")));
    }

    private Matcher<KenshooKeyword> isKeyword(Matcher<KenshooKeyword> ... matchers) {
        return allOf(matchers);
    }

    private Matcher<KenshooKeyword> withIdInTarget(int idInTarget) {
        return new ArgumentMatcher<KenshooKeyword>() {
            @Override
            public boolean matches(Object o) {
                return ((KenshooKeyword) o).idInTarget == idInTarget;
            }
        };
    }

    private Matcher<KenshooKeyword> withStatusInTarget(String statusInTarget) {
        return new ArgumentMatcher<KenshooKeyword>() {
            @Override
            public boolean matches(Object o) {
                return ((KenshooKeyword) o).statusInTarget.equals(statusInTarget);
            }
        };
    }

    private Matcher<KenshooKeyword> withText(String text) {
        return new ArgumentMatcher<KenshooKeyword>() {
            @Override
            public boolean matches(Object o) {
                return ((KenshooKeyword) o).text.equals(text);
            }
        };
    }

    private KenshooSelector fromCampaignId(int campaignId) {
        KenshooSelector selector = new KenshooSelector();
        selector.campaignIdInTargets.add(campaignId);
        return selector;
    }

    private GoogleKeywordBuilder withKeyword() {
        return new GoogleKeywordBuilder();
    }

    private void setupGoogleApi(GoogleKeyword ... keywords) {
        when(googleApi.getKeywords(any())).thenReturn(Arrays.asList(keywords));
    }


}
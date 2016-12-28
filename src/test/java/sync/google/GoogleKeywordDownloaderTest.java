package sync.google;

import com.google.adwords.*;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.jooq.lambda.Seq;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.allOf;
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
    private final static int CAMPAIGN_ID = 1000;

    @Mock
    GoogleKeywordService googleApi;

    GoogleKeywordDownloader testedDownloader;

    @Before
    public void setup() {
        testedDownloader = new GoogleKeywordDownloader(googleApi);
    }

    @Test
    public void testDownloadTwoGoogleKeywordsBelongsToSameCampaignId(){
        setupKeywordsInGoogle(
                keyword().withId(1).withCampaignId(CAMPAIGN_ID).withStatus("Active").withText("shoes"),
                keyword().withId(1).withCampaignId(CAMPAIGN_ID).withStatus("Paused").withText("shirts")
        );

        List<KenshooKeyword> kenshooKeywords = testedDownloader.download(
                    selector().withCampaignIdInTarget(CAMPAIGN_ID).build()
        );

        assertThat(kenshooKeywords, hasSize(2));
    }

    @Test
    public void testDownloadActiveGoogleKeyword(){
        setupKeywordsInGoogle(
                keyword().withId(1).withCampaignId(CAMPAIGN_ID).withStatus("Active").withText("shoes")
        );

        List<KenshooKeyword> kenshooKeywords = testedDownloader.download(
                    selector().withCampaignIdInTarget(CAMPAIGN_ID).build()
        );
        assertThat(kenshooKeywords.get(0), isKeyword(withIdInTarget(1), withStatus("Active"), withText("shoes")));
    }

    private Matcher<KenshooKeyword> withIdInTarget(Integer idInTarget) {
        return new ArgumentMatcher<KenshooKeyword>() {
            @Override
            public boolean matches(Object o) {
                return ((KenshooKeyword) o).idInTarget ==  idInTarget;
            }
        };
    }

    private Matcher<KenshooKeyword> withStatus(String status) {
        return new ArgumentMatcher<KenshooKeyword>() {
            @Override
            public boolean matches(Object o) {
                return ((KenshooKeyword)o).statusInTarget.equals(status);
            }
        };
    }

    private Matcher<KenshooKeyword> withText(String text) {
        return new ArgumentMatcher<KenshooKeyword>() {
            @Override
            public boolean matches(Object o) {
                return ((KenshooKeyword)o).text.equals(text);
            }
        };
    }

    private Matcher<KenshooKeyword> isKeyword(Matcher<KenshooKeyword>... matchers) {
        return allOf(matchers);
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

    private GoogleKeywordBuilder keyword() {
        return new GoogleKeywordBuilder();
    }

    private KenshooSelectorBuilder selector(){
        return  new KenshooSelectorBuilder();
    }

    private void setupKeywordsInGoogle(GoogleKeywordBuilder... keywordBuilder) {
        for(GoogleKeywordBuilder builder : keywordBuilder){
            builder.build();
        }
    }

    private Matcher<KenshooKeyword> hasText(String text) {

        return new ArgumentMatcher<KenshooKeyword>() {

            public boolean matches(Object o) {
                return ((KenshooKeyword)o).text.equals(text);
            }
        };
    }


}
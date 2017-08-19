package sync.google;

import com.google.adwords.*;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GoogleKeywordDownloaderTest {

    @Mock
    GoogleKeywordService googleApi;

    GoogleKeywordDownloader testedDownloader;

    private final static double FLOAT_ERROR_RANGE = 0;

    // -------------------------------------------------------------------------------------
    //     ________  ________   _____ ______________  ______
    //    /_  __/ / / / ____/  / ___// ____/_  __/ / / / __ \
    //    / / / /_/ / __/     \__ \/ __/   / / / / / / /_/ /
    //   / / / __  / /___    ___/ / /___  / / / /_/ / ____/
    //  /_/ /_/ /_/_____/   /____/_____/ /_/  \____/_/
    //
    // -------------------------------------------------------------------------------------
    //
    // This is the universe that is common to all tests.
    // In order to understand a single test scenario, you first have to learn and memorize
    // every detail of this universe.
    //
    // After refactoring, your final result should be without it.
    // Instead, you should have many small helper-methods to help each test to create
    // its own tiny 'world' independently, with only the minimal details required for that test.
    //
    // for instance:
    //
    // void after_successful_operation_all_rotten_teeth_shall_be_removed()
    // {
    //   Person me = someDefaultPerson().withTeeth(
    //       tooth().withId(1).withCondition(GOOD),
    //       tooth().withId(2).withCondition(ROTTEN),
    //       tooth().withId(3).withCondition(GOOD)
    //   );
    //
    //   doctor.operate(me);
    //
    //   assertThat(teethIdsIn(me), contains(1, 3));
    // }
    //
    //
    // -------------------------------------------------------------------------------------

    private final static int CAMPAIGN1_ID = 100;
    private final static int CAMPAIGN2_ID = 200;

    //
    // keyword 1
    //
    private final static int KEYWORD1_ID = 1;
    private final static int KEYWORD1_CAMPAIGN_ID = CAMPAIGN1_ID;
    private final static GoogleStatus KEYWORD1_STATUS = GoogleStatus.Active;
    private final static String KEYWORD1_TEXT = "shoes";
    private final static int KEYWORD1_BID_MICRO_AMOUNT = 500000;
    private final static GoogleKeyword KEYWORD1 = new GoogleKeywordBuilder()
            .withId(KEYWORD1_ID)
            .withCampaignId(KEYWORD1_CAMPAIGN_ID)
            .withStatus(KEYWORD1_STATUS)
            .withText(KEYWORD1_TEXT)
            .withBid(KEYWORD1_BID_MICRO_AMOUNT)
            .build();

    //
    // keyword 2 (deleted)
    //
    private final static int KEYWORD2_ID = 2;
    private final static int KEYWORD2_CAMPAIGN_ID = CAMPAIGN1_ID;
    private final static GoogleStatus KEYWORD2_STATUS = GoogleStatus.Deleted;
    private final static String KEYWORD2_TEXT = "shirts";
    private final static int KEYWORD2_BID_MICRO_AMOUNT = 1000000;
    private final static GoogleKeyword KEYWORD2 = new GoogleKeywordBuilder()
            .withId(KEYWORD2_ID)
            .withCampaignId(KEYWORD2_CAMPAIGN_ID)
            .withStatus(KEYWORD2_STATUS)
            .withText(KEYWORD2_TEXT)
            .withBid(KEYWORD2_BID_MICRO_AMOUNT)
            .build();

    //
    // keyword 3 (belongs to a different campaign)
    //
    private final static int KEYWORD3_ID = 3;
    private final static int KEYWORD3_CAMPAIGN_ID = CAMPAIGN2_ID;  //  <--- this is the different campaign! ha ha!
    private final static GoogleStatus KEYWORD3_STATUS = GoogleStatus.Paused;
    private final static String KEYWORD3_text = "wallets";
    private final static int KEYWORD3_BID_MICRO_AMOUNT = 200000;
    private final static GoogleKeyword KEYWORD3 = new GoogleKeywordBuilder()
            .withId(KEYWORD3_ID)
            .withCampaignId(KEYWORD3_CAMPAIGN_ID)
            .withStatus(KEYWORD3_STATUS)
            .withText(KEYWORD3_text)
            .withBid(KEYWORD3_BID_MICRO_AMOUNT)
            .build();


    @Before
    public void setup() {
        testedDownloader = new GoogleKeywordDownloader(googleApi);
    }

    @Test
    public void test_1() {
        //
        // new predicate
        //
        CampaignPredicate predicate = new CampaignPredicate();
        predicate.campaignIdInTargets.add(CAMPAIGN1_ID);
        predicate.campaignIdInTargets.add(CAMPAIGN2_ID);

        //
        // -----> Action!
        //
        testedDownloader.download(predicate);

        //
        // verify selector
        //
        ArgumentCaptor<GoogleSelector> captor = ArgumentCaptor.forClass(GoogleSelector.class);
        verify(googleApi).getKeywords(captor.capture());
        assertThat(captor.getValue().campaignIds, containsInAnyOrder(CAMPAIGN1_ID, CAMPAIGN2_ID));
    }

    @Test
    public void test_2() {

        when(googleApi.getKeywords(any())).thenReturn(Arrays.asList(KEYWORD1, KEYWORD2));

        //
        // new predicate
        //
        CampaignPredicate predicate = new CampaignPredicate();
        predicate.campaignIdInTargets.add(CAMPAIGN1_ID);

        //
        // -----> Action!
        //
        List<KenshooKeyword> kenshooKeywords = testedDownloader.download(predicate);

        assertThat(kenshooKeywords, hasSize(2));

        //
        // verify 1st keyword
        //
        assertThat(kenshooKeywords.get(0).idInTarget, equalTo(KEYWORD1_ID));
        assertThat(kenshooKeywords.get(0).status, equalTo(KenshooStatus.Active));
        assertThat(kenshooKeywords.get(0).text, equalTo(KEYWORD1_TEXT));
        assertThat((double)kenshooKeywords.get(0).bid, closeTo(0.5, FLOAT_ERROR_RANGE));

        //
        // verify 2nd keyword
        //
        assertThat(kenshooKeywords.get(1).idInTarget, equalTo(KEYWORD2_ID));
        assertThat(kenshooKeywords.get(1).status, equalTo(KenshooStatus.NonActive));
        assertThat(kenshooKeywords.get(1).text, equalTo(KEYWORD2_TEXT));
        assertThat("non-active keyword should be downloaded with bid=0", (double)kenshooKeywords.get(1).bid, closeTo(0, FLOAT_ERROR_RANGE));
    }
}

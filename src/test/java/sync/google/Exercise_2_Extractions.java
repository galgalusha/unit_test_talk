package sync.google;

import com.kenshoo.tracking.event.CampaignTrackingEventData;
import com.kenshoo.tracking.event.CampaignTrackingEventDataBuilder;
import com.kenshoo.tracking.event.CampaignTrackingEventDataMap;
import com.kenshoo.tracking.event.CampaignTrackingFileWritingData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.mockito.Mockito.when;

/**
 * Copied from class "CampaignTrackingEventDataMap" from KS.
 * Original comments...
 * User: shlomis
 * Date: 4/7/13
 * Time: 11:42 AM
 */
@RunWith(MockitoJUnitRunner.class)
public class Exercise_2_Extractions {

    private final static String LONG_FIELD_STRING = "123456";
    private final static String SHORT_FIELD_STRING = "123";


    @Test
    public void testNullCampId() throws Exception {
        CampaignTrackingEventDataMap campaignTrackingEventDataMap = new CampaignTrackingEventDataMap();
        CampaignTrackingEventData campaignTrackingEventData = CampaignTrackingEventDataBuilder.aTestCampaignTrackingEvent().withCampaignId(null).build();
        Map<CampaignTrackingFileWritingData.DatabaseColumns,String> databaseColumnsStringMap = campaignTrackingEventDataMap.PopulateMap(campaignTrackingEventData);
        assertNull(databaseColumnsStringMap.get(CampaignTrackingFileWritingData.DatabaseColumns.CAMPAIGN_ID));


    }

    @Test
    public void testNotNullCampId() throws Exception {
        CampaignTrackingEventDataMap campaignTrackingEventDataMap = new CampaignTrackingEventDataMap();
        CampaignTrackingEventData campaignTrackingEventData = CampaignTrackingEventDataBuilder.aTestCampaignTrackingEvent().withCampaignId(11).build();
        Map<CampaignTrackingFileWritingData.DatabaseColumns,String> databaseColumnsStringMap = campaignTrackingEventDataMap.PopulateMap(campaignTrackingEventData);
        assertEquals(databaseColumnsStringMap.get(CampaignTrackingFileWritingData.DatabaseColumns.CAMPAIGN_ID),"11");
    }

    @Test
    public void testNotNullTrackingExtendedId() throws Exception {
        CampaignTrackingEventDataMap campaignTrackingEventDataMap = new CampaignTrackingEventDataMap();
        CampaignTrackingEventData campaignTrackingEventData = CampaignTrackingEventDataBuilder.aTestCampaignTrackingEvent().withCampaignTrackingExtendedId(123456789456132L).build();
        Map<CampaignTrackingFileWritingData.DatabaseColumns,String> databaseColumnsStringMap = campaignTrackingEventDataMap.PopulateMap(campaignTrackingEventData);
        assertEquals(databaseColumnsStringMap.get(CampaignTrackingFileWritingData.DatabaseColumns.CAMPAIGN_TRACKING_EXTENDED_ID),"123456789456132");
    }

    @Test
    public void testChopToFitIsDoneForQueryString() throws Exception {
        CampaignTrackingEventDataMap campaignTrackingEventDataMap = getCampaignTrackingEventDataMapWithChopToFit();
        CampaignTrackingEventData campaignTrackingEventData = CampaignTrackingEventDataBuilder.aTestCampaignTrackingEvent().withQueryString(LONG_FIELD_STRING).build();
        Map<CampaignTrackingFileWritingData.DatabaseColumns,String> databaseColumnsStringMap = campaignTrackingEventDataMap.PopulateMap(campaignTrackingEventData);
        assertEquals(databaseColumnsStringMap.get(CampaignTrackingFileWritingData.DatabaseColumns.QUERY_STRING),SHORT_FIELD_STRING);
    }

    @Test
    public void testChopToFitIsDoneForReferer() throws Exception {
        CampaignTrackingEventDataMap campaignTrackingEventDataMap = getCampaignTrackingEventDataMapWithChopToFit();
        CampaignTrackingEventData campaignTrackingEventData = CampaignTrackingEventDataBuilder.aTestCampaignTrackingEvent().withReferer(LONG_FIELD_STRING).build();
        Map<CampaignTrackingFileWritingData.DatabaseColumns,String> databaseColumnsStringMap = campaignTrackingEventDataMap.PopulateMap(campaignTrackingEventData);
        assertEquals(databaseColumnsStringMap.get(CampaignTrackingFileWritingData.DatabaseColumns.REFERER),SHORT_FIELD_STRING);
    }

    @Test
    public void ipWithNoChangeWhenGlobalFalseAndEventTypeIsExeption() throws Exception {
        CampaignTrackingEventDataMap campaignTrackingEventDataMap = new CampaignTrackingEventDataMap();
        CampaignTrackingEventData campaignTrackingEventData = CampaignTrackingEventDataBuilder.aTestCampaignTrackingEvent().withIp("1921685477").build();
        Map<CampaignTrackingFileWritingData.DatabaseColumns,String> databaseColumnsStringMap = campaignTrackingEventDataMap.PopulateMap(campaignTrackingEventData);
        assertEquals(databaseColumnsStringMap.get(CampaignTrackingFileWritingData.DatabaseColumns.IP_MYSQL_PLACE_HOLDER),"1921685477");
    }


    private CampaignTrackingEventDataMap getCampaignTrackingEventDataMapWithChopToFit() {
        return new CampaignTrackingEventDataMap() {
            @Override
            public String chopToFit(CampaignTrackingFileWritingData.DatabaseColumns column, String value) {
                return value.substring(0, SHORT_FIELD_STRING.length());
            }
        };
    }
}

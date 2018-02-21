package com.kenshoo.tracking.event;



import java.util.HashMap;
import java.util.Map;

import static com.kenshoo.tracking.event.CampaignTrackingFileWritingData.DatabaseColumns.*;


public class CampaignTrackingEventDataMap {

    private static final String DEFAULT_EVENT_STATUS = "not handled";
    private static final String CAMPAIGN_TRACKING = "campaign_tracking";

    public Map<CampaignTrackingFileWritingData.DatabaseColumns, String> PopulateMap(CampaignTrackingEventData eventData) {
        final Map<CampaignTrackingFileWritingData.DatabaseColumns, String> values = new HashMap<CampaignTrackingFileWritingData.DatabaseColumns, String>();
        values.put(EVENT_TYPE, String.valueOf(eventData.getEventType()));
        values.put(TYPE, eventData.getType());
        values.put(DATE, eventData.getDate().toString());
        values.put(PROFILE_TOKEN, eventData.getProfileToken());
        values.put(PROFILE_ID, nullOrIntValue(eventData.getProfileId()));
        values.put(CAMPAIGN_ID, nullOrIntValue(eventData.getCampaignId()));
        values.put(ADGROUP_ID, nullOrIntValue(eventData.getAdGroupId()));
        values.put(AFFCODE, eventData.getAffcode());
        values.put(CRITERIA, eventData.getCriteria());
        values.put(KEYWORDS, eventData.getKeywords());
        values.put(CRITERIA_TYPE, eventData.getMatchType());
        values.put(PRODUCT, eventData.getProduct());
        values.put(CREATIVE_ID_IN_TARGET, eventData.getCreativeIdInTarget());
        values.put(REVENUE, eventData.getValue());
        values.put(COOKIE, eventData.getCookie());
        values.put(ORDER_ID, eventData.getOrderId());
        values.put(IP_MYSQL_PLACE_HOLDER, getIp(eventData));
        values.put(PROMO_CODE, eventData.getPromoCode());
        values.put(CURRENCY, eventData.getCurrency());
        values.put(NETWORK_TYPE, eventData.getNetworkType());
        values.put(QUERY_STRING, chopToFit(QUERY_STRING, eventData.getQueryString()));
        values.put(USER_AGENT, eventData.getUserAgent());
        values.put(REFERER, chopToFit(REFERER, eventData.getReferer()));
        values.put(RAW_DEVICE, eventData.getRawDevice());
        values.put(STATUS, DEFAULT_EVENT_STATUS);
        values.put(SITELINK_ID, eventData.getSitelinkId());
        values.put(CRITERION_ID_IN_TARGET, eventData.getCriterionIdInTarget());
        values.put(ADGROUP_ID_IN_TARGET, eventData.getAdGroupIdInTarget());
        values.put(CAMPAIGN_ID_IN_TARGET, eventData.getCampaignIdInTarget());
        values.put(MERCHANT_CENTER_ID_IN_TARGET, eventData.getMerchantCenterIdInTarget());
        values.put(CAMPAIGN_TRACKING_EXTENDED_ID, nullOrLongValue(eventData.getCampaignTrackingExtendedId()));
        values.put(CHANNEL_NAME, eventData.getChannelName());
        values.put(FEED_ITEM_ID, eventData.getFeedItemId());
        return values;
    }


    public String chopToFit(CampaignTrackingFileWritingData.DatabaseColumns column, String value) {
        return value.substring(0, 32);
    }

    static String nullOrIntValue(Integer value) {
        return (value == null) ? null : value.toString();
    }

    static String nullOrLongValue(Long value) {
        return (value == null) ? null : value.toString();
    }

    private String getIp(CampaignTrackingEventData eventData) {
        return eventData.getIp();
    }

}
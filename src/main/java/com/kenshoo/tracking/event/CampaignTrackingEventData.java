package com.kenshoo.tracking.event;


import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 * User: eyals
 * Date: 3/7/13
 * Time: 11:42 AM
 */
public final class CampaignTrackingEventData {
    private final int eventType;
    private final String type;
    private final LocalDateTime date;

    private final String profileToken;
    private final Integer profileId;
    private final Integer campaignId;
    private final Integer adGroupId;
    private final String affcode;
    private final String criteria;
    private final String keywords;
    private final String matchType;
    private final String product;
    private final String creativeIdInTarget;

    private final String value;
    private final String cookie;
    private final String orderId;
    private final String ip;
    private final String promoCode;
    private final String currency;
    private final String networkType;
    private final String queryString;
    private final String userAgent;
    private final String referer;
    private final String rawDevice;
    private final String sitelinkId;
    private final String criterionIdInTarget;
    private final String adGroupIdInTarget;
    private final String campaignIdInTarget;
    private final String merchantCenterIdInTarget;
    private final Long campaignTrackingExtendedId;
    private final String channelName;
    private boolean isException;
    private final String feedItemId;
    private boolean isUniversalCampaign;


    private CampaignTrackingEventData(int eventType,
                                      String type,
                                      LocalDateTime date,
                                      String profileToken,
                                      Integer profileId,
                                      Integer campaignId,
                                      Integer adGroupId,
                                      String affcode,
                                      String criteria,
                                      String keywords,
                                      String matchType,
                                      String product,
                                      String creativeIdInTarget,
                                      String value,
                                      String cookie,
                                      String orderId,
                                      String ip,
                                      String promoCode,
                                      String currency,
                                      String networkType,
                                      String queryString,
                                      String userAgent,
                                      String referer,
                                      boolean isException,
                                      String rawDevice,
                                      String sitelinkId,
                                      String criterionIdInTarget,
                                      String adGroupIdInTarget,
                                      String campaignIdInTarget,
                                      String merchantCenterIdInTarget,
                                      Long campaignTrackingExtendedId,
                                      String channelName,
                                      String feedItemId,
                                      boolean isUniversalCampaign)
    {
        this.eventType = eventType;
        this.type = type;
        this.date = date;
        this.profileToken = profileToken;
        this.profileId = profileId;
        this.campaignId = campaignId;
        this.adGroupId = adGroupId;
        this.affcode = affcode;
        this.criteria = criteria;
        this.keywords = keywords;
        this.matchType = matchType;
        this.product = product;
        this.creativeIdInTarget = creativeIdInTarget;
        this.value = value;
        this.cookie = cookie;
        this.orderId = orderId;
        this.ip = ip;
        this.promoCode = promoCode;
        this.currency = currency;
        this.networkType = networkType;
        this.queryString = queryString;
        this.userAgent = userAgent;
        this.referer = referer;
        this.rawDevice = rawDevice;
        this.isException = isException;
        this.sitelinkId = sitelinkId;
        this.criterionIdInTarget = criterionIdInTarget;
        this.adGroupIdInTarget = adGroupIdInTarget;
        this.campaignIdInTarget = campaignIdInTarget;
        this.merchantCenterIdInTarget = merchantCenterIdInTarget;
        this.campaignTrackingExtendedId = campaignTrackingExtendedId;
        this.channelName = channelName;
        this.feedItemId = feedItemId;
        this.isUniversalCampaign = isUniversalCampaign;
    }

    static CampaignTrackingEventData aCampaignTrackingEventData(int eventType,
                                                                String type,
                                                                LocalDateTime date,
                                                                String profileToken,
                                                                Integer profileId,
                                                                Integer campaignId,
                                                                Integer adGroupId,
                                                                String affcode,
                                                                String criteria,
                                                                String keywords,
                                                                String matchType,
                                                                String product,
                                                                String creativeIdInTarget,
                                                                String value,
                                                                String cookie,
                                                                String orderId,
                                                                String ip,
                                                                String promoCode,
                                                                String currency,
                                                                String networkType,
                                                                String queryString,
                                                                String userAgent,
                                                                String referer,
                                                                boolean isException,
                                                                String rawDevice,
                                                                String sitelinkId,
                                                                String criterionIdInTarget,
                                                                String adGroupIdInTarget,
                                                                String campaignIdInTarget,
                                                                String merchantCenterIdInTarget,
                                                                Long campaignTrackingExtendedId,
                                                                String channelName,
                                                                String feedItemId,
                                                                boolean isUniversalCampaign) {
        return new CampaignTrackingEventData(eventType, type, date, profileToken, profileId, campaignId, adGroupId, affcode, criteria, keywords,
                matchType, product, creativeIdInTarget, value, cookie, orderId, ip, promoCode, currency, networkType, queryString,
                userAgent, referer, isException, rawDevice, sitelinkId, criterionIdInTarget, adGroupIdInTarget, campaignIdInTarget, merchantCenterIdInTarget, campaignTrackingExtendedId, channelName,feedItemId, isUniversalCampaign);
    }

    public int getEventType() {
        return eventType;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getProfileToken() {
        return profileToken;
    }

    public Integer getProfileId() {
        return profileId;
    }

    public Integer getCampaignId() {
        return campaignId;
    }

    public Integer getAdGroupId() {
        return adGroupId;
    }

    public String getAffcode() {
        return affcode;
    }

    public String getCriteria() {
        return criteria;
    }

    public String getKeywords() {
        return keywords;
    }

    public String getMatchType() {
        return matchType;
    }

    public String getProduct() {
        return product;
    }

    public String getCreativeIdInTarget() {
        return creativeIdInTarget;
    }

    public String getValue() {
        return value;
    }

    public String getCookie() {
        return cookie;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getIp() {
        return ip;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public String getCurrency() {
        return currency;
    }

    public String getNetworkType() {
        return networkType;
    }

    public String getQueryString() {
        return queryString;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getReferer() {
        return referer;
    }

    public String getRawDevice() {
        return rawDevice;
    }

    public String getSitelinkId() {
        return sitelinkId;
    }

    public String getCriterionIdInTarget() {
        return criterionIdInTarget;
    }

    public String getAdGroupIdInTarget() {
        return adGroupIdInTarget;
    }

    public String getCampaignIdInTarget() {
        return campaignIdInTarget;
    }

    public boolean isException() {
        return isException;
    }

    public void setException(boolean exception) {
        isException = exception;
    }

    public String getMerchantCenterIdInTarget() {
        return merchantCenterIdInTarget;
    }

    public Long getCampaignTrackingExtendedId() {
        return campaignTrackingExtendedId;
    }

    public String getChannelName() {
        return channelName;
    }

    public String getFeedItemId() {
        return feedItemId;
    }

    public boolean isUniversalCampaign() {
        return isUniversalCampaign;
    }
}

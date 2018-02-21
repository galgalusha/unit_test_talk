package com.kenshoo.tracking.event;


import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 * User: eyals
 * Date: 3/11/13
 * Time: 12:00 PM
 */
public class CampaignTrackingEventDataBuilder {
    private int eventType;
    private String type;
    private LocalDateTime date;

    private String profileToken;
    private Integer profileId;
    private Integer campaignId;
    private Integer adGroupId;
    private String affcode;
    private String criteria;
    private String keywords;
    private String matchType;
    private String product;
    private String creativeIdInTarget;

    private String value;
    private String cookie;
    private String orderId;
    private String ip;
    private String promoCode;
    private String currency;
    private String networkType;
    private String queryString;
    private String userAgent;
    private String referer;
    private String rawDevice;
    private String sitelinkId;
    private String criterionIdInTarget;
    private String adGroupIdInTarget;
    private String campaignIdInTarget;
    private String merchantCenterIdInTarget;
    private Long campaignTrackingExtendedId;
    private String channelName;
    private boolean isException;
    private String feedItemId;
    private boolean isUniversalCampaign;

    public static final Integer PROFILE_ID = 49;

    private CampaignTrackingEventDataBuilder() {
    }

    public static CampaignTrackingEventDataBuilder aCampaignTrackingEvent() {
        return new CampaignTrackingEventDataBuilder();
    }

    public static CampaignTrackingEventDataBuilder aTestCampaignTrackingEvent() {
        final CampaignTrackingEventDataBuilder eventDataBuilder = new CampaignTrackingEventDataBuilder();
        eventDataBuilder.eventType = 0;
        eventDataBuilder.type = "click";
        eventDataBuilder.date = LocalDateTime.now();
        eventDataBuilder.profileToken = "token";
        eventDataBuilder.profileId = PROFILE_ID;
        eventDataBuilder.campaignId = 21;
        eventDataBuilder.adGroupId = 14;
        eventDataBuilder.affcode = "kw76652";
        eventDataBuilder.criteria = "My Criteria";
        eventDataBuilder.keywords = "My Keyword";
        eventDataBuilder.matchType = "Broad";
        eventDataBuilder.product = "product";
        eventDataBuilder.creativeIdInTarget = "426842";
        eventDataBuilder.value = "874.64";
        eventDataBuilder.cookie = "cookie";
        eventDataBuilder.orderId = "orderId";
        eventDataBuilder.ip = "1921685477";
        eventDataBuilder.promoCode = "promoCode";
        eventDataBuilder.currency = "USD";
        eventDataBuilder.networkType = "Search";
        eventDataBuilder.queryString = "queryString";
        eventDataBuilder.userAgent = "Chrome";
        eventDataBuilder.referer = "referer";
        eventDataBuilder.rawDevice = "d";
        eventDataBuilder.isException = false;
        eventDataBuilder.sitelinkId = "12345";
        eventDataBuilder.criterionIdInTarget = "kwd-111:aud-123";
        eventDataBuilder.adGroupIdInTarget = "6666";
        eventDataBuilder.campaignIdInTarget = "1212";
        eventDataBuilder.merchantCenterIdInTarget = "123123";
        eventDataBuilder.campaignTrackingExtendedId = 3856271565948341028L;
        eventDataBuilder.channelName = "google";
        eventDataBuilder.feedItemId = "7676";
        eventDataBuilder.isUniversalCampaign = false;
        return eventDataBuilder;
    }

    public CampaignTrackingEventDataBuilder copy(CampaignTrackingEventData data) {
        return withAdGroupId(data.getAdGroupId())
                .withAffcode(data.getAffcode())
                .withCampaignId(data.getCampaignId())
                .withCookie(data.getCookie())
                .withCreativeIdInTarget(data.getCreativeIdInTarget())
                .withCriteria(data.getCriteria())
                .withCurrency(data.getCurrency())
                .withDate(data.getDate())
                .withEventType(data.getEventType())
                .withIp(data.getIp())
                .withKeywords(data.getKeywords())
                .withMatchType(data.getMatchType())
                .withNetworkType(data.getNetworkType())
                .withOrderId(data.getOrderId())
                .withProduct(data.getProduct())
                .withProfileId(data.getProfileId())
                .withProfileToken(data.getProfileToken())
                .withPromoCode(data.getPromoCode())
                .withQueryString(data.getQueryString())
                .withRawDevice(data.getRawDevice())
                .withReferer(data.getReferer())
                .withType(data.getType())
                .withUserAgent(data.getUserAgent())
                .withValue(data.getValue())
                .withSitelinkId(data.getSitelinkId())
                .withCriterionIdInTarget(data.getCriterionIdInTarget())
                .withAdgroupIdInTarget(data.getAdGroupIdInTarget())
                .withCampaignIdInTarget(data.getCampaignIdInTarget())
                .withMerchantCenterIdInTarget(data.getMerchantCenterIdInTarget())
                .withCampaignTrackingExtendedId(data.getCampaignTrackingExtendedId())
                .withChannelName(data.getChannelName())
                .withFeedItemId(data.getFeedItemId())
                .isUniversalCampaign(data.isUniversalCampaign());
    }

    public CampaignTrackingEventDataBuilder withEventType(int eventType) {
        this.eventType = eventType;
        return this;
    }

    public CampaignTrackingEventDataBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public CampaignTrackingEventDataBuilder withDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    public CampaignTrackingEventDataBuilder withProfileToken(String profileToken) {
        this.profileToken = profileToken;
        return this;
    }

    public CampaignTrackingEventDataBuilder withProfileId(Integer profileId) {
        this.profileId = profileId;
        return this;
    }

    public CampaignTrackingEventDataBuilder withCampaignId(Integer campaignId) {
        this.campaignId = campaignId;
        return this;
    }

    public CampaignTrackingEventDataBuilder withAdGroupId(Integer adGroupId) {
        this.adGroupId = adGroupId;
        return this;
    }

    public CampaignTrackingEventDataBuilder withAffcode(String affcode) {
        this.affcode = affcode;
        return this;
    }

    public CampaignTrackingEventDataBuilder withCriteria(String criteria) {
        this.criteria = criteria;
        return this;
    }

    public CampaignTrackingEventDataBuilder withKeywords(String keywords) {
        this.keywords = keywords;
        return this;
    }

    public CampaignTrackingEventDataBuilder withMatchType(String matchType) {
        this.matchType = matchType;
        return this;
    }

    public CampaignTrackingEventDataBuilder withProduct(String product) {
        this.product = product;
        return this;
    }

    public CampaignTrackingEventDataBuilder withCreativeIdInTarget(String creativeIdInTarget) {
        this.creativeIdInTarget = creativeIdInTarget;
        return this;
    }

    public CampaignTrackingEventDataBuilder withValue(String value) {
        this.value = value;
        return this;
    }

    public CampaignTrackingEventDataBuilder withCookie(String cookie) {
        this.cookie = cookie;
        return this;
    }

    public CampaignTrackingEventDataBuilder withOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public CampaignTrackingEventDataBuilder withIp(String ip) {
        this.ip = ip;
        return this;
    }

    public CampaignTrackingEventDataBuilder withPromoCode(String promoCode) {
        this.promoCode = promoCode;
        return this;
    }

    public CampaignTrackingEventDataBuilder withCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public CampaignTrackingEventDataBuilder withNetworkType(String networkType) {
        this.networkType = networkType;
        return this;
    }

    public CampaignTrackingEventDataBuilder withQueryString(String queryString) {
        this.queryString = queryString;
        return this;
    }

    public CampaignTrackingEventDataBuilder withUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    public CampaignTrackingEventDataBuilder withReferer(String referer) {
        this.referer = referer;
        return this;
    }

    public CampaignTrackingEventDataBuilder withRawDevice(String rawDevice) {
        this.rawDevice = rawDevice;
        return this;
    }

    public CampaignTrackingEventDataBuilder withSitelinkId(String sitelinkId) {
        this.sitelinkId = sitelinkId;
        return this;
    }

    public CampaignTrackingEventDataBuilder withCriterionIdInTarget(String criterionIdInTarget) {
        this.criterionIdInTarget = criterionIdInTarget;
        return this;
    }

    public CampaignTrackingEventDataBuilder withAdgroupIdInTarget(String adgroupIdInTarget) {
        this.adGroupIdInTarget = adgroupIdInTarget;
        return this;
    }

    public CampaignTrackingEventDataBuilder withCampaignIdInTarget(String campaignIdInTarget) {
        this.campaignIdInTarget = campaignIdInTarget;
        return this;
    }

    public CampaignTrackingEventDataBuilder withMerchantCenterIdInTarget(String merchantCenterIdInTarget) {
        this.merchantCenterIdInTarget = merchantCenterIdInTarget;
        return this;
    }

    public CampaignTrackingEventDataBuilder withCampaignTrackingExtendedId(Long campaignTrackingExtendedId) {
        this.campaignTrackingExtendedId = campaignTrackingExtendedId;
        return this;
    }

    public CampaignTrackingEventDataBuilder withChannelName(String channelName) {
        this.channelName = channelName;
        return this;
    }

    public CampaignTrackingEventDataBuilder isException(boolean isException) {
        this.isException = isException;
        return this;
    }

    public CampaignTrackingEventDataBuilder withFeedItemId(String feedItemId) {
        this.feedItemId = feedItemId;
        return this;
    }

    public CampaignTrackingEventDataBuilder isUniversalCampaign(boolean isUniversalCampaign) {
        this.isUniversalCampaign = isUniversalCampaign;
        return this;
    }

    public CampaignTrackingEventData build() {
        return CampaignTrackingEventData.aCampaignTrackingEventData(eventType, type, date, profileToken, profileId, campaignId,
                adGroupId, affcode, criteria, keywords, matchType, product, creativeIdInTarget, value, cookie,
                orderId, ip, promoCode, currency, networkType, queryString, userAgent, referer, isException, rawDevice, sitelinkId,
                criterionIdInTarget, adGroupIdInTarget, campaignIdInTarget, merchantCenterIdInTarget, campaignTrackingExtendedId,
                channelName, feedItemId, isUniversalCampaign);
    }

}

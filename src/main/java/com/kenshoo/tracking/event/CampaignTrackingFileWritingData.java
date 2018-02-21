package com.kenshoo.tracking.event;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * Created with IntelliJ IDEA.
 * User: eyals
 * Date: 3/13/13
 * Time: 11:43 AM
 */
public abstract class CampaignTrackingFileWritingData {

    public static final String TRACKING_STREAM_EVENTS_DIR = "tracking_stream_events";
    public static final String IP_CONVERSION_MYSQL_STATEMENT = "client_ip_numeric = INET_ATON(@client_ip_numeric)";
    public static final String LINE_SEPARATOR = "\r\n";
    public static final char FIELD_SEPARATOR = ',';

    public enum DatabaseColumns {
        EVENT_TYPE("event_type"),
        TYPE("type"),
        DATE("create_date"),
        PROFILE_TOKEN("profile_token"),
        PROFILE_ID("profile_id"),
        CAMPAIGN_ID("campaign_id"),
        ADGROUP_ID("adgroup_id"),
        AFFCODE("affcode"),
        CRITERIA("criteria"),
        KEYWORDS("keywords"),
        CRITERIA_TYPE("criteria_type"),
        PRODUCT("product"),
        CREATIVE_ID_IN_TARGET("creative_id_in_target"),
        REVENUE("value"),
        COOKIE("cookies"),
        ORDER_ID("order_id"),
        IP_MYSQL_PLACE_HOLDER("@client_ip_numeric"),
        PROMO_CODE("promo_code"),
        CURRENCY("value_currency"),
        NETWORK_TYPE("network_type"),
        QUERY_STRING("query_string"),
        USER_AGENT("user_agent"),
        REFERER("referer"),
        RAW_DEVICE("raw_device"),
        STATUS("status"),
        SITELINK_ID("sitelink_id"),
        CRITERION_ID_IN_TARGET("criterion_id_in_target"),
        ADGROUP_ID_IN_TARGET("ad_id_in_target"),
        CAMPAIGN_ID_IN_TARGET("campaign_id_in_target"),
        MERCHANT_CENTER_ID_IN_TARGET("mc_id_in_target"),
        CAMPAIGN_TRACKING_EXTENDED_ID("campaign_tracking_extended_id"),
        CHANNEL_NAME("channel_name"),
        FEED_ITEM_ID("feed_item_id");

        private String columnName;

        DatabaseColumns(String columnName) {
            this.columnName = columnName;
        }

        public static List<String> columnNames() {
            final List<String> columnNames = new ArrayList<String>();

            for (DatabaseColumns value : values()) {
                columnNames.add(value.columnName);
            }

            return columnNames;
        }
    }

    public List<String> columnNames() {
        return DatabaseColumns.columnNames();
    }

    public Optional<String> ipConversion() {
        return Optional.of(IP_CONVERSION_MYSQL_STATEMENT);

    }

}

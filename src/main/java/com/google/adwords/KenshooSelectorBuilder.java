package com.google.adwords;

import sync.google.KenshooSelector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 12/28/2016.
 */
public class KenshooSelectorBuilder {

        private List<Integer> campaignIdInTargets;

        public KenshooSelectorBuilder() {
        }

        public KenshooSelectorBuilder campaignIdInTargets(List<Integer> val) {
            campaignIdInTargets = val;
            return this;
        }

        public KenshooSelectorBuilder  withCampaignIdInTarget(Integer val) {
            campaignIdInTargets.add(val);
            return this;
        }

        public KenshooSelector build() {
            return new KenshooSelector(campaignIdInTargets);
        }
}

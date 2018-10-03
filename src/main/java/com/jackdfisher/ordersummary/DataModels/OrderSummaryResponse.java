package com.jackdfisher.ordersummary.DataModels;

public class OrderSummaryResponse {
    private SummaryPricing pricing;
    private SummaryState finalState;

    public OrderSummaryResponse(SummaryPricing pricing, SummaryState finalState) {
        this.pricing = pricing;
        this.finalState = finalState;
    }
}
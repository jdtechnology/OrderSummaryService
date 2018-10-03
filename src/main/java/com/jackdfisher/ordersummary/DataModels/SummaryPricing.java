package com.jackdfisher.ordersummary.DataModels;

public class SummaryPricing {
    private SummaryChanges changes;
    private SummaryAmount currentMonthlyTotal;
    private SummaryAmount newMonthlyTotal;

    public SummaryPricing(SummaryChanges changes, SummaryAmount currentMonthlyTotal, SummaryAmount newMonthlyTotal) {
        this.changes = changes;
        this.currentMonthlyTotal = currentMonthlyTotal;
        this.newMonthlyTotal = newMonthlyTotal;
    }
}
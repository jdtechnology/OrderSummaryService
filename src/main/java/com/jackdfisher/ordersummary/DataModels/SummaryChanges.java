package com.jackdfisher.ordersummary.DataModels;

public class SummaryChanges {
    private SummaryAmount recurring;
    private SummaryAmount nonRecurring;

    public SummaryChanges(SummaryAmount recurring, SummaryAmount nonRecurring) {
        this.recurring = recurring;
        this.nonRecurring = nonRecurring;
    }

}
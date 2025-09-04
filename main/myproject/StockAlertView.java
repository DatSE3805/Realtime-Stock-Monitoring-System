package com.myproject;

import java.util.HashMap;
import java.util.Map;

public class StockAlertView implements StockViewer {
    private double alertThresholdHigh;
    private double alertThresholdLow;
    private Map<String, Double> lastAlertedPrices = new HashMap<>();

    public StockAlertView(double highThreshold, double lowThreshold) {
        // CONSTRUCTOR
        this.alertThresholdHigh = highThreshold;
        this.alertThresholdLow = lowThreshold;
    }

    @Override
    public void onUpdate(StockPrice stockPrice) {
        // TO DO: ALERT LOGIC
        String code = stockPrice.getCode();
        double price = stockPrice.getAvgPrice();

        Double lastPrice = lastAlertedPrices.get(code);
            
        if (lastPrice == null || !lastPrice.equals(price)) {
            if (price >= alertThresholdHigh || price <= alertThresholdLow) {
                Logger.logAlert(code, price);
            }
        }
        lastAlertedPrices.put(code, price);
    }

    private void alertAbove(String stockCode, double price) {
        Logger.notImplementedYet("alertAbove");
    }

    private void alertBelow(String stockCode, double price) {
        Logger.notImplementedYet("alertBelow");
    }
}
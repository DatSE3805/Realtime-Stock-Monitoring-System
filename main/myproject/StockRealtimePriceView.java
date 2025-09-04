package com.myproject;

import java.util.HashMap;
import java.util.Map;

public class StockRealtimePriceView implements StockViewer {
    private final Map<String, Double> lastPrices = new HashMap<>();

    @Override
    public void onUpdate(StockPrice stockPrice) {
        // TO DO
        String code = stockPrice.getCode();
        double newPrice = stockPrice.getAvgPrice();

        synchronized (code.intern()) {
            Double lastPrice = lastPrices.get(code);

            if (lastPrice == null) {
                lastPrices.put(code, newPrice);
            }
            else if (!lastPrice.equals(newPrice)) {
                lastPrices.remove(code,lastPrice);
                lastPrices.put(code, newPrice);
                Logger.logRealtime(code, newPrice);
            }
        }
    }
}
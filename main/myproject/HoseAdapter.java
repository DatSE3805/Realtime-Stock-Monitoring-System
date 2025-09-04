package com.myproject;

import java.util.ArrayList;
import java.util.List;

public class HoseAdapter implements PriceFetcher {
    private HosePriceFetchLib hoseLib;
    private List<String> stockCodes;
 
    public HoseAdapter(HosePriceFetchLib hoseLib, List<String> stockCodes) {
        // TO DO: CONSTRUCTOR
        this.hoseLib = hoseLib;
        this.stockCodes = stockCodes;
    }

    @Override
    public List<StockPrice> fetch() {
        // TO DO: Fetch stock data and convert it to StockPrice list
        List<HoseData> hoseDataList = hoseLib.getPrices(stockCodes);
        List<StockPrice> result = new ArrayList<>();

        for (HoseData data : hoseDataList) {
            StockPrice stockPrice = convertToStockPrice(data);
            result.add(stockPrice);
        }

        return result;
    }

    private StockPrice convertToStockPrice(HoseData hoseData) {
        // TO DO: Convert HoseData to StockPrice
        return new StockPrice(hoseData.getStockCode(),hoseData.getPrice(),hoseData.getVolume(),hoseData.getTimestamp());
    }
}
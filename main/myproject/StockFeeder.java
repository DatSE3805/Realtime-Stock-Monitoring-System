package com.myproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StockFeeder {
    private List<Stock> stockList = new ArrayList<>();
    private Map<String, List<StockViewer>> viewers = new HashMap<>();
    private static StockFeeder instance = null;

    private StockFeeder() { }

    public static StockFeeder getInstance() {
        if (instance == null) {
            synchronized (StockFeeder.class) {
                if (instance == null) {
                    instance = new StockFeeder();
                }
            }
        }
        return instance;
    }

    public void addStock(Stock stock) {
        for (Stock a : stockList) {
            if(a.getCode().equals(stock.getCode())) {
                if(a.getName().equals(stock.getName())) {
                    return;
                }
                else {
                    stockList.remove(a);
                    break;
                }
            }
        }

        stockList.add(stock);
    }

    public void registerViewer(String code, StockViewer stockViewer) {
        boolean isExist = false;
        for (Stock stock : stockList) {
            if (stock.getCode().equals(code)) {
                isExist = true;
                break;
            }
        }
        if (!isExist) {
            Logger.errorRegister(code);
            return;
        }

        if (!viewers.containsKey(code)) {
            viewers.put(code, new ArrayList<>());
        }
        else {
            List<StockViewer> stockViewers = viewers.get(code);
            for (StockViewer existingViewer : stockViewers) {
                if (existingViewer.getClass().equals(stockViewer.getClass())) {
                    Logger.errorRegister(code);
                    return;
                }
            }
        }

        viewers.get(code).add(stockViewer);
    }

    public void unregisterViewer(String code, StockViewer stockViewer) {
        if (!viewers.containsKey(code)) {
            Logger.errorUnregister(code);
            return;
        }

        List<StockViewer> stockViewers = viewers.get(code);
        if (stockViewers.contains(stockViewer)) {
            stockViewers.remove(stockViewer);
        }
        else {
            Logger.errorUnregister(code);
        }
    }

    public void notify(StockPrice stockPrice) {
        String code = stockPrice.getCode();
        if(!viewers.containsKey(code)) {
            return;
        }

        List<StockViewer> stockViewers = viewers.get(code);
        if (stockViewers != null) {
            for (StockViewer viewer : stockViewers) {
                viewer.onUpdate(stockPrice);
            }
        }
    }
}
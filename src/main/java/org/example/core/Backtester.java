package org.example.core;

import org.example.Trade;

import java.util.ArrayList;
import java.util.List;

public class Backtester {
    private final Data data;
    private Strategy strategy;
    private final List<Trade> trades = new ArrayList<>();

    public Backtester(Data data, Strategy strategy) {
        this.data = data;
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void run() {
        strategy.init();
        for (int i = 0; i < data.size(); i++) {
            strategy.next(i);
        }
    }

    public void buy(int i, double price, int size) {
        trades.add(new Trade(i, price, size, "BUY"));
    }

    public void sell(int i, double price, int size) {
        trades.add(new Trade(i, price, size, "SELL"));
    }

    public List<Trade> getTrades() {
        return trades;
    }
}

package org.example;

import java.util.List;

public class Broker {
    private List<Trade> trades;

    public Broker(List<Trade> trades) {
        this.trades = trades;
    }

    public List<Trade> getTrades() {
        return trades;
    }
}


package org.example.strategy;
import org.example.core.Strategy;
import org.example.core.Data;

import org.example.core.Backtester;
import org.example.indicator.Indicator;

public class SmaCrossStrategy extends Strategy {
    private double[] smaFast;
    private double[] smaSlow;

    public SmaCrossStrategy(Data data, Backtester broker) {
        super(data, broker);
    }

    @Override
    public void init() {
        smaFast = Indicator.sma(data, 3);
        smaSlow = Indicator.sma(data, 5);
    }

    @Override
    public void next(int i) {
        if (i < 5) return;
        if (smaFast[i] > smaSlow[i] && smaFast[i - 1] <= smaSlow[i - 1]) {
            broker.buy(i, data.getClose(i), 1);
        } else if (smaFast[i] < smaSlow[i] && smaFast[i - 1] >= smaSlow[i - 1]) {
            broker.sell(i, data.getClose(i), 1);
        }
    }
}
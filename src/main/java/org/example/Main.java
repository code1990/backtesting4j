package org.example;

import org.example.core.Backtester;
import org.example.core.Data;
import org.example.strategy.SmaCrossStrategy;

public class Main {
    public static void main(String[] args) {
        double[] close = {10, 10.5, 11, 10.8, 11.2, 12, 11.5, 12.1, 12.3, 12.5, 12.8, 13, 13.5};
        double[] open = close, high = close, low = close, volume = new double[close.length];

        Data data = new Data(open, high, low, close, volume);
        Backtester backtester = new Backtester(data, new SmaCrossStrategy(data, null));
        SmaCrossStrategy strategy = new SmaCrossStrategy(data, backtester);
        backtester.setStrategy(strategy);
        backtester.run();

        for (Trade trade : backtester.getTrades()) {
            System.out.printf("Trade: %s at %.2f on candle %d\n", trade.getType(), trade.getPrice(), trade.getTimeIndex());
        }
    }
}
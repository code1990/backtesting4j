package org.example.core;

public class Data {
    private final double[] open, high, low, close, volume;

    public Data(double[] open, double[] high, double[] low, double[] close, double[] volume) {
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
    }

    public double getOpen(int i) { return open[i]; }
    public double getHigh(int i) { return high[i]; }
    public double getLow(int i) { return low[i]; }
    public double getClose(int i) { return close[i]; }
    public double getVolume(int i) { return volume[i]; }
    public int size() { return close.length; }
}
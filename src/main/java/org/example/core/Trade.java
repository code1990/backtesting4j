package org.example.core;

public class Trade {
    private final int timeIndex;
    private final double price;
    private final int size;
    private final String type;

    public Trade(int timeIndex, double price, int size, String type) {
        this.timeIndex = timeIndex;
        this.price = price;
        this.size = size;
        this.type = type;
    }

    public int getTimeIndex() { return timeIndex; }
    public double getPrice() { return price; }
    public int getSize() { return size; }
    public String getType() { return type; }
}

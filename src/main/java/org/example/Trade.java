package org.example;

public class Trade {
    private double size;
    private double entryPrice;
    private double pl;

    public Trade(int i, double price, int size, String buy) {
    }

    public double getSize() {
        return size;
    }

    public double getEntryPrice() {
        return entryPrice;
    }

    public double getPL() {
        return pl;
    }

    public void close(double portion) {
        // 示例实现，视具体逻辑而定
        System.out.println("Closing trade with portion: " + portion);
    }

    public Object getType() {
    }

    public Object getPrice() {
    }
}

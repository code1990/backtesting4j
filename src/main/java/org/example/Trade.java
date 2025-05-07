package org.example;

public class Trade {
    private double size;
    private double entryPrice;
    private double pl;

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
}

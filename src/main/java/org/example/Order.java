package org.example;

public class Order {
    private boolean isContingent;

    public Order(boolean isContingent) {
        this.isContingent = isContingent;
    }

    public boolean isContingent() {
        return isContingent;
    }

    public void cancel() {
        System.out.println("Order cancelled.");
    }
}
package org.example.core;

public abstract class Strategy {
    protected Data data;
    protected Backtester broker;

    public Strategy(Data data, Backtester broker) {
        this.data = data;
        this.broker = broker;
    }

    public abstract void init();
    public abstract void next(int i);
}
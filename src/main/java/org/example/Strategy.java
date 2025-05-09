package org.example;

import com.sun.corba.se.pept.broker.Broker;
import org.example.core.Data;
import org.example.indicator.Indicator;

import java.util.*;
import java.util.function.Function;

public abstract class Strategy {
    protected Broker broker;
    protected Data data;
    protected Map<String, Object> params;
    protected List<Indicator> indicators = new ArrayList<>();

    public Strategy(Broker broker, Data data, Map<String, Object> params) {
        this.broker = broker;
        this.data = data;
        this.params = checkParams(params);
    }

    private Map<String, Object> checkParams(Map<String, Object> params) {
        for (String key : params.keySet()) {
            try {
                this.getClass().getDeclaredField(key);
            } catch (NoSuchFieldException e) {
                throw new IllegalArgumentException("Strategy '" + this.getClass().getSimpleName()
                        + "' is missing parameter '" + key + "'. Please define it as a field.");
            }
        }
        return params;
    }

    public Indicator I(Function<Object[], double[]> func, Object[] args, String name) {
        double[] result = func.apply(args);
        Indicator indicator = new Indicator(result, name);
        indicators.add(indicator);
        return indicator;
    }

    public abstract void init();  // 初始化指标、数据等

    public abstract void next();  // 每个时间步执行的操作
}


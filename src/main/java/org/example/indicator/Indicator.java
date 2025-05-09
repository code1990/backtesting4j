package org.example.indicator;

import org.example.core.Data;

public class Indicator {


    public static double[] sma(Data data, int period) {
        double[] close = new double[data.size()];
        for (int i = 0; i < data.size(); i++) {
            close[i] = data.getClose(i);
        }

        double[] result = new double[close.length];
        for (int i = 0; i < close.length; i++) {
            if (i < period - 1) {
                result[i] = Double.NaN;
                continue;
            }
            double sum = 0.0;
            for (int j = i - period + 1; j <= i; j++) {
                sum += close[j];
            }
            result[i] = sum / period;
        }
        return result;
    }
}

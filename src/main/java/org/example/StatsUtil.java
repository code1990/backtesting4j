package org.example;

import java.util.*;

public class StatsUtil {

    public static class Result {
        public final List<Integer> durations;
        public final List<Double> peakDrawdowns;

        public Result(List<Integer> durations, List<Double> peakDrawdowns) {
            this.durations = durations;
            this.peakDrawdowns = peakDrawdowns;
        }
    }

    /**
     * 计算每段回撤的持续长度和最大回撤。
     * @param dd 回撤序列，例如 [0.0, -0.1, -0.2, 0.0, -0.3, -0.4, 0.0]
     * @return 回撤持续时间 和 最大回撤 的列表（与输入长度一致，其他位置为 null）
     */
    public static Result computeDrawdownDurationPeaks(List<Double> dd) {
        int n = dd.size();
        List<Integer> durations = new ArrayList<>(Collections.nCopies(n, null));
        List<Double> peakDrawdowns = new ArrayList<>(Collections.nCopies(n, null));

        List<Integer> zeroIndices = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (dd.get(i) == 0.0) {
                zeroIndices.add(i);
            }
        }
        if (zeroIndices.isEmpty() || zeroIndices.get(zeroIndices.size() - 1) != n - 1) {
            zeroIndices.add(n - 1); // 确保最后一段被考虑
        }

        for (int i = 1; i < zeroIndices.size(); i++) {
            int prev = zeroIndices.get(i - 1);
            int curr = zeroIndices.get(i);

            if (curr > prev + 1) {  // 有真正的回撤段
                int duration = curr - prev;
                double peak = Collections.max(dd.subList(prev, curr + 1));

                durations.set(curr, duration);
                peakDrawdowns.set(curr, peak);
            }
        }

        return new Result(durations, peakDrawdowns);
    }

    /**
     * 计算几何平均收益率。
     *
     * @param returns 收益率序列，例如 [0.01, -0.02, 0.03]
     * @return 几何平均收益率
     */
    public static double geometricMean(List<Double> returns) {
        if (returns == null || returns.isEmpty()) {
            return 0.0;
        }

        int n = returns.size();
        double product = 1.0;

        for (double r : returns) {
            double adjusted = r + 1.0;

            if (Double.isNaN(adjusted)) {
                adjusted = 1.0; // fillna(0) + 1
            }

            if (adjusted <= 0) {
                return 0.0;  // 与 Python 版本一致
            }

            product *= adjusted;
        }

        return Math.pow(product, 1.0 / n) - 1.0;
    }

    // 简单测试
    public static void main(String[] args) {
        List<Double> dd = Arrays.asList(0.0, -0.1, -0.2, 0.0, -0.3, -0.4, 0.0);
        Result result = computeDrawdownDurationPeaks(dd);

        System.out.println("Durations: " + result.durations);
        System.out.println("Peak Drawdowns: " + result.peakDrawdowns);

        List<Double> returns = Arrays.asList(0.01, -0.02, 0.03);
        double result2 = geometricMean(returns);
        System.out.printf("Geometric Mean: %.6f\n", result2);
    }
}


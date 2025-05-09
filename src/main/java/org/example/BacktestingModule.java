package org.example;

import java.util.concurrent.*;
import java.util.logging.Logger;

public class BacktestingModule {

    public static String VERSION;

    static {
        try {
            // 模拟从文件或类中加载版本信息
            VERSION = VersionLoader.getVersion();
        } catch (Exception e) {
            VERSION = "?.?.?";  // 未安装或加载失败
        }
    }

    // 模拟库类加载（在 Java 中通常是显式导入类）
    static {
        try {
            Class.forName("com.example.backtesting.lib.Lib");
            Class.forName("com.example.backtesting.plotting.Plotting");
            Class.forName("com.example.backtesting.core.Backtest");
            Class.forName("com.example.backtesting.core.Strategy");
        } catch (ClassNotFoundException e) {
            System.err.println("模块类加载失败: " + e.getMessage());
        }
    }

    private static final Logger LOGGER = Logger.getLogger(BacktestingModule.class.getName());

    /**
     * 提供线程池，用于并行优化
     */
    public static ExecutorService getPool(int threads) {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            LOGGER.warning("当前操作系统可能使用的是 spawn 式进程模型（如 Windows）。"
                    + "建议使用线程池或确保调用包含在 main 方法中。"
                    + "当前返回基于线程的线程池。详情见 GitHub Issue #1256");

            // 使用线程池代替进程池
            return Executors.newFixedThreadPool(threads);
        } else {
            // 在 Linux/Unix 上仍然使用线程池（Java 没有原生进程池）
            return Executors.newFixedThreadPool(threads);
        }
    }

}

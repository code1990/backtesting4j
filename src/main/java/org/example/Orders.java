package org.example;

import java.util.*;

public class Orders {
    private final List<Order> orders;

    public Orders(List<Order> orders) {
        this.orders = orders;
    }

    // 取消所有非条件订单（非 SL/TP）
    public void cancel() {
        for (Order order : orders) {
            if (!order.isContingent()) {
                order.cancel();
            }
        }
    }

    public Order get(int index) {
        return orders.get(index);
    }

    public int size() {
        return orders.size();
    }

    // 模拟旧接口访问报错提示
    public Object getDeprecated(String attr) {
        Set<String> removed = Set.of("entry", "set_entry", "is_long", "is_short",
                "sl", "tp", "set_sl", "set_tp");
        if (removed.contains(attr)) {
            throw new UnsupportedOperationException(
                    "Strategy.orders." + attr + " was removed in version 0.2.0. Use `Order` API instead."
            );
        }
        throw new IllegalArgumentException("No such attribute: " + attr);
    }
}


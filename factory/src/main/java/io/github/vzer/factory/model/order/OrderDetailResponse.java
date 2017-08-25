package io.github.vzer.factory.model.order;

import java.io.Serializable;

/**
 * @author YangCihang
 * @since 17/8/24.
 * email yangcihang@hrsoft.net
 */

public class OrderDetailResponse implements Serializable {
    private OrderDetailModel order;

    public void setOrder(OrderDetailModel order) {
        this.order = order;
    }

    public OrderDetailModel getOrder() {
        return order;
    }
}

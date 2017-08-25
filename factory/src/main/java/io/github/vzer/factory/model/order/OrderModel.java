package io.github.vzer.factory.model.order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author YangCihang
 * @since 17/8/23.
 * email yangcihang@hrsoft.net
 */

public class OrderModel implements Serializable {
    public static final int STATE_NO_PAYMENT = 0; //已提交
    public static final int STATE_DISTRIBUTE = 1; //已配送
    public static final int STATE_FINISH = 2;//已完成
    private String orderId;
    private int status;
    private double total;
    private ArrayList<String> productNames;

    public void setProductNames(ArrayList<String> productNames) {
        this.productNames = productNames;
    }

    public ArrayList<String> getProductNames() {
        return productNames;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public int getStatus() {
        return status;
    }

    public String getOrderId() {
        return orderId;
    }
}

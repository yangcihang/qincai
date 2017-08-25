package io.github.vzer.factory.model.order;

import java.io.Serializable;
import java.util.List;

/**
 * @author YangCihang
 * @since 17/8/1.
 * email yangcihang@hrsoft.net
 */

public class OrderDetailModel implements Serializable {

    public static final int STATE_SUBMIT = 0;
    public static final int STATE_DISTRIBUTE = 1;
    public static final int STATE_FINISH = 2;

    private String orderId; //订单id
    private long createdAt; //创建时间
    private double total; //总金额
    private List<OrderDetailVegetableModel> productList;//商品列表
    private String addOn; //备注
    private int status; //订单状态

    private String place; //收货地点

    public String getOrderId() {
        return orderId;
    }

    public long getCreatedAt() {
        return createdAt;
    }


    public double getTotal() {
        return total;
    }

    public String getPlace() {
        return place;
    }

    public String getAddOn() {
        return addOn;
    }

    public List<OrderDetailVegetableModel> getProductList() {
        return productList;
    }

    public int getStatus() {
        return status;
    }

    public void setOrderId(String  orderId) {
        this.orderId = orderId;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }


    public void setTotal(double total) {
        this.total = total;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setAddOn(String addOn) {
        this.addOn = addOn;
    }

    public void setProductList(List<OrderDetailVegetableModel> productList) {
        this.productList = productList;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

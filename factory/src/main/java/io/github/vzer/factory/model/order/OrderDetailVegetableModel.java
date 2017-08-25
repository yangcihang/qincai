package io.github.vzer.factory.model.order;

import java.io.Serializable;

/**
 * 订单详情里面的蔬菜列表model
 *
 * @author YangCihang
 * @since 17/8/4.
 * email yangcihang@hrsoft.net
 */

public class OrderDetailVegetableModel implements Serializable {
    //        "productId": "1",
//                "stock": "3",
//                "productName": "香菜",
//                "productPrice": "1.5"

    private String productName;
    private String productId;
    private String productPrice;
    private String stock;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
}

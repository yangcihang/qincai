package io.github.vzer.factory.model.order;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author YangCihang
 * @since 17/8/23.
 * email yangcihang@hrsoft.net
 */

public class OrderListResponse implements Serializable {
    private ArrayList<OrderModel> list;
    private boolean isLastPage;

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }

    public void setList(ArrayList<OrderModel> list) {
        this.list = list;
    }

    public ArrayList<OrderModel> getList() {
        return list;
    }

    public boolean isLastPage() {
        return isLastPage;
    }
}

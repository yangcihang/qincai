package io.github.vzer.factory.presenter.order;

import java.util.List;

import io.github.vzer.common.factory.presenter.BaseContract;
import io.github.vzer.factory.model.order.DiscussModel;
import io.github.vzer.factory.model.order.OrderDetailModel;
import io.github.vzer.factory.model.order.OrderModel;

/**
 * @author YangCihang
 * @since 17/8/23.
 * email yangcihang@hrsoft.net
 */

public interface OrderDetailContract {
    interface View extends BaseContract.View<Presenter> {
        void loadOrderListSuccess(OrderDetailModel detailModel);
        void loadOrderListFailed();
    }

    interface Presenter extends BaseContract.Presenter {
        // TODO: 17/8/4 发送请求订单信息
        void requestOrderDetail(String orderId,int type);

    }
}

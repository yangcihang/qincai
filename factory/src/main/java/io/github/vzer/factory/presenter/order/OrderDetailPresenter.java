package io.github.vzer.factory.presenter.order;

import io.github.vzer.common.factory.presenter.BasePresenter;
import io.github.vzer.factory.R;
import io.github.vzer.factory.data.OrderHelper;
import io.github.vzer.factory.model.order.OrderDetailModel;
import io.github.vzer.factory.model.order.OrderModel;
import io.github.vzer.factory.utils.ToastUtil;

/**
 * @author YangCihang
 * @since 17/8/23.
 * email yangcihang@hrsoft.net
 */

public class OrderDetailPresenter extends BasePresenter<OrderDetailContract.View>
        implements OrderDetailContract.Presenter {
    /**
     * P层构造方法;
     * 创建P层时就进行双向绑定
     *
     * @param mView V层的引用
     */
    public OrderDetailPresenter(OrderDetailContract.View mView) {
        super(mView);
    }

    @Override
    public void requestOrderDetail(String orderId, int type) {
        switch (type) {
            case OrderModel.STATE_NO_PAYMENT:
                OrderHelper.getNoPaymentOrderDetail(orderId, this);
                break;
            case OrderModel.STATE_FINISH:
                OrderHelper.getCompleteOrderDetail(orderId, this);
                break;
            case OrderModel.STATE_DISTRIBUTE:
                OrderHelper.getNoPickOrderDetail(orderId, this);
                break;
            default:
                ToastUtil.showToast("逻辑不可达");
                break;
        }
    }

    public void onDataLoadSuccess(OrderDetailModel orderDetailModel) {
        if (orderDetailModel != null) {
            mView.loadOrderListSuccess(orderDetailModel);
        } else {
            onDataLoadFailed();
        }
    }

    public void onDataLoadFailed() {
        mView.loadOrderListFailed();
    }
}

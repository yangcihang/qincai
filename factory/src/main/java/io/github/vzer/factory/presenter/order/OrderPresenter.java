package io.github.vzer.factory.presenter.order;

import java.util.List;

import io.github.vzer.common.factory.presenter.BasePresenter;
import io.github.vzer.factory.data.OrderHelper;
import io.github.vzer.factory.model.order.OrderModel;

/**
 * @author YangCihang
 * @since 17/7/27.
 * email yangcihang@hrsoft.net
 */

public class OrderPresenter extends BasePresenter<OrderContract.View>
        implements OrderContract.Presenter {
    /**
     * P层构造方法;
     * 创建P层时就进行双向绑定
     *
     * @param mView V层的引用
     */
    public OrderPresenter(OrderContract.View mView) {
        super(mView);
    }

    /**
     * 获取所有订单
     */
    @Override
    public void loadAllOrder(int page, int rows) {
        OrderHelper.getAllOrderList(page, rows, this);
    }

    /**
     * 获取未支付订单
     */
    @Override
    public void loadNoPaymentOrder(int page, int rows) {
        OrderHelper.getNoPaymentOrderList(page, rows, this);
    }

    /**
     * 获取已完成订单
     */
    @Override
    public void loadCompleteOrder(int page, int rows) {
        OrderHelper.getCompleteOrderList(page, rows, this);

    }

    /**
     * 获取未取货订单
     */
    @Override
    public void loadNoPickOder(int page, int rows) {
        OrderHelper.getNoPickOrderList(page, rows, this);

    }

    /**
     * 发送评论成功时
     */
    public void onSendDiscussRequestSuccess() {
        // TODO: 17/8/7 通知v层跳转
    }

    /**
     * 数据加载成功时
     */
    public void onDataLoadSuccess(List<OrderModel> models, boolean isLastPage) {
        mView.loadOrderListSuccess(models, isLastPage);
    }

    /**
     * 数据加载失败时
     */
    public void onDataLoadFailed() {
        mView.loadOrderListFailed();
    }
}

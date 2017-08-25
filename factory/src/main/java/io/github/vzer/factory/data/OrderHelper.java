package io.github.vzer.factory.data;

import java.util.List;

import io.github.vzer.common.factory.data.DataCallback;
import io.github.vzer.factory.model.account.AccountRspModel;
import io.github.vzer.factory.model.order.DiscussModel;
import io.github.vzer.factory.model.order.OrderDetailModel;
import io.github.vzer.factory.model.order.OrderDetailResponse;
import io.github.vzer.factory.model.order.OrderListResponse;
import io.github.vzer.factory.model.order.OrderModel;
import io.github.vzer.factory.network.NetWork;
import io.github.vzer.factory.network.ResponseCallback;
import io.github.vzer.factory.presenter.order.OrderDetailPresenter;
import io.github.vzer.factory.presenter.order.OrderPresenter;

/**
 * @author YangCihang
 * @since 17/8/4.
 * email yangcihang@hrsoft.net
 */

public class OrderHelper {
    /**
     * 获取orderDetail的ModelList
     *
     * @param callback dataCallback
     */
    public static void getAllOrderList(int page, int rows, final OrderPresenter callback) {
        NetWork.getService()
                .requestAllOrderList(page, rows)
                .enqueue(new ResponseCallback<OrderListResponse>(new ResponseCallback.DataCallback() {
                    @Override
                    public void onDataSuccess(Object data) {
                        OrderListResponse response = (OrderListResponse) data;
                        callback.onDataLoadSuccess(response.getList(), response.isLastPage());
                    }

                    @Override
                    public void onDataFailed(int errorCode) {
                        // TODO: 17/8/23 失败时提示
                        callback.onDataLoadFailed();
                    }
                }));
    }

    /**
     * 获取已完成列表的请求
     */
    public static void getCompleteOrderList(int page, int rows, final OrderPresenter callback) {
        NetWork.getService()
                .requestCompleteOrderList(page, rows)
                .enqueue(new ResponseCallback<OrderListResponse>(new ResponseCallback.DataCallback() {
                    @Override
                    public void onDataSuccess(Object data) {
                        OrderListResponse response = (OrderListResponse) data;
                        callback.onDataLoadSuccess(response.getList(), response.isLastPage());
                    }

                    @Override
                    public void onDataFailed(int errorCode) {
                        callback.onDataLoadFailed();
                    }
                }));
    }

    /**
     * 获取未取货的订单列表
     */
    public static void getNoPickOrderList(int page, int rows, final OrderPresenter callback) {
        NetWork.getService()
                .requestNoPickOrderList(page, rows)
                .enqueue(new ResponseCallback<OrderListResponse>(new ResponseCallback.DataCallback() {
                    @Override
                    public void onDataSuccess(Object data) {
                        OrderListResponse response = (OrderListResponse) data;
                        callback.onDataLoadSuccess(response.getList(), response.isLastPage());
                    }

                    @Override
                    public void onDataFailed(int errorCode) {
                        callback.onDataLoadFailed();
                    }
                }));
    }

    /**
     * 获取未支付订单列表
     */
    public static void getNoPaymentOrderList(int page, int rows, final OrderPresenter callback) {
        NetWork.getService()
                .requestNoPaymentOrderList(page, rows)
                .enqueue(new ResponseCallback<OrderListResponse>(new ResponseCallback.DataCallback() {
                    @Override
                    public void onDataSuccess(Object data) {
                        OrderListResponse response = (OrderListResponse) data;
                        callback.onDataLoadSuccess(response.getList(), response.isLastPage());
                    }

                    @Override
                    public void onDataFailed(int errorCode) {
                        callback.onDataLoadFailed();
                    }
                }));
    }

    /**
     * 获取未支付订单详情
     */
    public static void getNoPaymentOrderDetail(String orderId, final OrderDetailPresenter callback) {
        NetWork.getService()
                .requestNoPaymentOrderDetail(orderId)
                .enqueue(new ResponseCallback<OrderDetailResponse>(new ResponseCallback.DataCallback() {
                    @Override
                    public void onDataSuccess(Object data) {
                        OrderDetailResponse response = (OrderDetailResponse) data;
                        callback.onDataLoadSuccess(response.getOrder());
                    }

                    @Override
                    public void onDataFailed(int errorCode) {
                        callback.onDataLoadFailed();
                    }
                }));
    }

    /**
     * 获取未提货订单详情
     */
    public static void getNoPickOrderDetail(String orderId, final OrderDetailPresenter callback) {
        NetWork.getService()
                .requestNoPickOrderDetail(orderId)
                .enqueue(new ResponseCallback<OrderDetailResponse>(new ResponseCallback.DataCallback() {
                    @Override
                    public void onDataSuccess(Object data) {
                        OrderDetailResponse response = (OrderDetailResponse) data;
                        callback.onDataLoadSuccess(response.getOrder());
                    }

                    @Override
                    public void onDataFailed(int errorCode) {
                        callback.onDataLoadFailed();
                    }
                }));
    }

    /**
     * 获取已完成订单详情
     */
    public static void getCompleteOrderDetail(String orderId, final OrderDetailPresenter callback) {
        NetWork.getService()
                .requestFinishedOrderDetail(orderId)
                .enqueue(new ResponseCallback<OrderDetailResponse>(new ResponseCallback.DataCallback() {
                    @Override
                    public void onDataSuccess(Object data) {
                        OrderDetailResponse response = (OrderDetailResponse) data;
                        callback.onDataLoadSuccess(response.getOrder());
                    }

                    @Override
                    public void onDataFailed(int errorCode) {
                        callback.onDataLoadFailed();
                    }
                }));
    }


    /**
     * 发送评论的请求
     *
     * @param model    model
     * @param callback dataCallback
     */
    public static void sendDiscussRequest(DiscussModel model, OrderPresenter callback) {
        callback.onSendDiscussRequestSuccess();
    }
}


package io.github.vzer.factory.network;

import io.github.vzer.factory.model.account.AccountRspModel;
import io.github.vzer.factory.model.account.LoginModel;
import io.github.vzer.factory.model.account.RegisterModel;
import io.github.vzer.factory.model.mine.coupon.CouponModel;
import io.github.vzer.factory.model.mine.coupon.CouponResponse;
import io.github.vzer.factory.model.mine.wallet.UserWalletOrderModel;
import io.github.vzer.factory.model.mine.wallet.UserWalletOrderResponse;
import io.github.vzer.factory.model.order.OrderDetailModel;
import io.github.vzer.factory.model.order.OrderDetailResponse;
import io.github.vzer.factory.model.order.OrderListResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 网络接口请求类
 *
 * @author: Vzer.
 * @date: 2017/7/25. 15:54
 * @email: vzer@qq.com
 */

public interface RemoteService {

    /**
     * 登录接口
     *
     * @param model 出入LoginModel
     * @return 返回 <RspModel<AccountRspModel>>
     */
    // TODO: 2017/7/26 后端给接口
    @POST("user/login")
    Call<RspModel<AccountRspModel>> login(@Body LoginModel model);

    @POST("user")
    Call<RspModel<AccountRspModel>> register(@Body RegisterModel model);

    @GET("user/trade/list")
    Call<RspModel<UserWalletOrderResponse>> requestAllWalletOrder(@Query("page") int pageId, @Query("rows") int rowsId);

    @GET("user/trade/withdraw/request")
    Call<RspModel<UserWalletOrderResponse>> requestWithdrawWalletOrder(@Query("page") int pageId, @Query("rows") int rowsId);

    @GET("user/trade/back/request")
    Call<RspModel<UserWalletOrderResponse>> requestRefundsWalletOrder(@Query("page") int pageId, @Query("rows") int rowsId);

    @GET("user/coupons")
    Call<RspModel<CouponResponse>> requestCoupon();

    /**
     * 获取所有信息列表
     */
    @GET("order/all")
    Call<RspModel<OrderListResponse>> requestAllOrderList(@Query("page")int pageId,@Query("size")int rows);

    /**
     * 获取完成订单列表
     */
    @GET("order/history")
    Call<RspModel<OrderListResponse>>requestCompleteOrderList(@Query("page")int pageId,@Query("size")int rows);

    /**
     * 获取未支付订单列表
     */
    @GET("order/unpay")
    Call<RspModel<OrderListResponse>> requestNoPaymentOrderList(@Query("page")int pageId,@Query("size")int rows);

    /**
     * 获取未取货订单列表
     */
    @GET("order/pay")
    Call<RspModel<OrderListResponse>> requestNoPickOrderList(@Query("page")int pageId,@Query("size")int rows);

    /**
     * 完成的订单详情
     */
    @GET("order/history/{orderId}")
    Call<RspModel<OrderDetailResponse>>requestFinishedOrderDetail(@Path("orderId") String  orderId);

    /**
     * 未支付订单详情
     */
    @GET("order/unpay/{orderId}")
    Call<RspModel<OrderDetailResponse>>requestNoPaymentOrderDetail(@Path("orderId") String  orderId);

    /**
     * 未提货订单详情
     */
    @GET("order/pay/{orderId}")
    Call<RspModel<OrderDetailResponse>>requestNoPickOrderDetail(@Path("orderId") String  orderId);

}

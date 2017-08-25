package io.github.vzer.sharevegetable.order.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.github.vzer.common.app.ToolbarActivityPresenter;
import io.github.vzer.common.widget.ScreenUtil;
import io.github.vzer.factory.constant.KeyConstant;
import io.github.vzer.factory.model.order.OrderDetailModel;
import io.github.vzer.factory.model.order.OrderDetailVegetableModel;
import io.github.vzer.factory.presenter.order.OrderDetailContract;
import io.github.vzer.factory.presenter.order.OrderDetailPresenter;
import io.github.vzer.factory.utils.TimeUtil;
import io.github.vzer.factory.utils.ToastUtil;
import io.github.vzer.sharevegetable.R;
import io.github.vzer.sharevegetable.order.adapter.OrderDetailListAdapter;
import io.github.vzer.sharevegetable.widget.NoTouchRecyclerView;

public class OrderDetailActivity extends ToolbarActivityPresenter<OrderDetailContract.Presenter>
        implements OrderDetailContract.View {

    @BindView(R.id.rec_order_detail)
    NoTouchRecyclerView orderDetailRec;
    @BindView(R.id.txt_payment_state)
    TextView paymentStateTxt;
    @BindView(R.id.txt_order_state_describe)
    TextView describeStateTxt;
    @BindView(R.id.txt_contract)
    TextView contractTxt;
    @BindView(R.id.txt_order_money)
    TextView orderMoneyTxt;
    @BindView(R.id.txt_order_describe)
    TextView describeTxt;
    @BindView(R.id.txt_order_time)
    TextView orderTimeTxt;
    @BindView(R.id.txt_order_number)
    TextView orderNumTxt;
    @BindView(R.id.txt_copy)
    TextView copyTxt;
    @BindView(R.id.btn_to_discuss)
    Button toDiscussBtn;
    @BindView(R.id.scroll_order_detail)
    ScrollView orderDetailScroll;
    @BindView(R.id.btn_order_cancel)
    Button cancelBtn;
    @BindView(R.id.btn_to_pay)
    Button payBtn;
    @BindView(R.id.ly_payment_state)
    RelativeLayout paymentStateLy;

    private OrderDetailListAdapter adapter;
    private List<OrderDetailVegetableModel> list;
    private OrderDetailModel orderDetailModel; //订单
    private LinearLayoutManager manager;
    private int type;

    /**
     * 静态启动
     */
    public static void start(Context context, String orderId, int type) {
        Intent intent = new Intent(context, OrderDetailActivity.class);
        intent.putExtra(KeyConstant.KEY_ORDER_ID, orderId);
        intent.putExtra(KeyConstant.KEY_ORDER_TYPE, type);
        context.startActivity(intent);
    }

    /**
     * 初始化数据，做网络加载
     */
    @Override
    protected void initData() {
        String orderId = getIntent().getStringExtra(KeyConstant.KEY_ORDER_ID);
        type = getIntent().getIntExtra(KeyConstant.KEY_ORDER_TYPE, -1);
        mPresenter.requestOrderDetail(orderId, type);
    }

    @Override
    public void initWidget() {
        setActivityTitle(getResources().getString(R.string.title_order_detail));


    }

    @Override
    protected void initWindows() {

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_order_detail;
    }

    /**
     * 初始化list
     */
    private void initList() {
        list = new ArrayList<>();
        list = orderDetailModel.getProductList();
        adapter = new OrderDetailListAdapter(this, list);
        orderDetailRec.setAdapter(adapter);
        manager = new LinearLayoutManager(this);
        orderDetailRec.setLayoutManager(manager);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) orderDetailRec.getLayoutParams();
        // TODO: 17/8/24 用更优雅的办法
        params.height = ScreenUtil.dip2px(56) * adapter.getItemCount(); //计算rec高度，避免滑动
        orderDetailRec.setLayoutParams(params);
        orderDetailRec.setFocusable(false);
    }

    /**
     * 初始化视图
     */
    private void initView() {
        switch (type) {
            case OrderDetailModel.STATE_SUBMIT:
                paymentStateTxt.setText("去支付");
                paymentStateLy.setVisibility(View.VISIBLE);

                break;
            case OrderDetailModel.STATE_DISTRIBUTE:
                paymentStateTxt.setText("待配送");
                paymentStateLy.setVisibility(View.GONE);
                describeStateTxt.setVisibility(View.VISIBLE);
                break;
            case OrderDetailModel.STATE_FINISH:
                paymentStateTxt.setText("已完成");
                paymentStateLy.setVisibility(View.GONE);
                toDiscussBtn.setVisibility(View.VISIBLE);
                break;
        }
        orderNumTxt.setText(orderDetailModel.getOrderId());
        orderMoneyTxt.setText("￥" + String.valueOf(orderDetailModel.getTotal()));
        describeTxt.setText(orderDetailModel.getAddOn());
        String orderTime = TimeUtil.setStampToString(orderDetailModel.getCreatedAt(),TimeUtil.DATETIME_DEFAULT_FORMAT);
        orderTimeTxt.setText(orderTime);
    }

    /**
     * 点击联系商家
     */
    @OnClick(R.id.txt_contract)
    void onContract() {
        String phoneNum = getResources().getString(R.string.text_phone_num);
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNum));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    /**
     * 点击去评价
     */
    @OnClick(R.id.btn_to_discuss)
    void toDiscuss() {
        if (toDiscussBtn.getVisibility() == View.VISIBLE) {
            startActivity(new Intent(this, DiscussActivity.class));
        }
    }

    /**
     * 点击取消
     */
    @OnClick(R.id.btn_order_cancel)
    void toCancel() {
        // TODO: 17/8/10 发送取消订单的请求(订单有时间限制 )
    }

    /**
     * 点击去付款
     */
    @OnClick(R.id.btn_to_pay)
    void toPay() {
        ToastUtil.showToast("去付款");
    }

    /**
     * 点击复制文本
     */
    @OnClick(R.id.txt_copy)
    void copyOrderNum() {
        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData mClipData = ClipData.newPlainText("Label", orderNumTxt.getText());
        if (cm != null) {
            cm.setPrimaryClip(mClipData);
            ToastUtil.showToast(R.string.toast_copy_success);
        } else {
            ToastUtil.showToast(R.string.toast_copy_error);
        }

    }


    /**
     * 加载订单列表成功时回调
     */
    @Override
    public void loadOrderListSuccess(OrderDetailModel detailModel) {
        //异步返回成功
        this.orderDetailModel = detailModel;
        initList();
        initView();
    }


    /**
     * 加载订单列表失败时回调
     */
    @Override
    public void loadOrderListFailed() {
        // TODO: 17/8/23 数据加载失败时回调
    }


    @Override
    public OrderDetailContract.Presenter initPresenter() {
        return new OrderDetailPresenter(this);
    }

    @Override
    public void showError(int strId) {

    }

    @Override
    public void showLoading() {

    }
}

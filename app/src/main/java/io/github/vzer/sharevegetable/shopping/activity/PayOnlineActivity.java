package io.github.vzer.sharevegetable.shopping.activity;

import android.content.Intent;

import io.github.vzer.common.app.ToolbarActivityPresenter;
import io.github.vzer.factory.presenter.vegetable.VegetableContract;
import io.github.vzer.sharevegetable.R;
import io.github.vzer.sharevegetable.order.activity.OrderDetailActivity;

/**
 * @author abtion.
 * @since 17/8/6 17:15.
 * email caiheng@hrsoft.net
 */

public class PayOnlineActivity extends ToolbarActivityPresenter<VegetableContract.Presenter> {

    @Override
    public void showError(int strId) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public VegetableContract.Presenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void initWidget() {
        setActivityTitle("在线支付");
    }

    @Override
    protected void initWindows() {

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_pay_online;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, OrderDetailActivity.class);
        startActivity(intent);
        finish();
        // TODO: 17/8/6 接入微信支付sdk 
    }
}
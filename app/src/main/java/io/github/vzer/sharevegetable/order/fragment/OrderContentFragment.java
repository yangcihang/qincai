package io.github.vzer.sharevegetable.order.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

import butterknife.BindView;
import io.github.vzer.common.app.FragmentPresenter;
import io.github.vzer.common.widget.recycler.RecyclerFooterAdapter;
import io.github.vzer.common.widget.recycler.RecyclerScrollListener;
import io.github.vzer.factory.model.order.OrderDetailModel;
import io.github.vzer.factory.model.order.OrderModel;
import io.github.vzer.factory.presenter.order.OrderContract;
import io.github.vzer.factory.presenter.order.OrderPresenter;
import io.github.vzer.factory.utils.ToastUtil;
import io.github.vzer.factory.utils.Utility;
import io.github.vzer.sharevegetable.R;
import io.github.vzer.sharevegetable.order.activity.OrderDetailActivity;
import io.github.vzer.sharevegetable.order.adapter.OrderContentListAdapter;

/**
 * @author YangCihang
 * @since 17/7/29.
 * email yangcihang@hrsoft.net
 */

@SuppressLint("ValidFragment")
public class OrderContentFragment extends FragmentPresenter<OrderContract.Presenter>
        implements OrderContract.View {

    public static final int PAGER_ALL = 0;
    public static final int PAGER_NO_PAYMENT = 1;
    public static final int PAGER_NO_PICK_UP = 2;
    public static final int PAGER_COMPLETE = 3;
    private int pagerType = -1; //页面类型，根据不同类型发送不同请求
    @BindView(R.id.rec_order_content)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_ly)
    SwipeRefreshLayout refreshLayout;

    private OrderContentListAdapter adapter;
    private List<OrderModel> contentList;
    private int page = 1; //页数
    private final int rows = 8; //默认一页8条
    private boolean isLastPage = false;

    public OrderContentFragment(int pagerType) {
        this.pagerType = pagerType;
    }

    @Override
    protected void initData() {
        requestDataList();
    }


    @Override
    protected void initWidget(View root) {
        initList();
        refreshLayout.setColorSchemeResources(R.color.colorPrimary);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Utility.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        if (!refreshLayout.isRefreshing()) {
                        isLastPage = false;
                        page = 1;
                        //清空数据列表
                        refreshLayout.setRefreshing(true);
                        adapter.setToRefresh(true);
                        requestDataList();
//                        }
                    }
                });
            }
        });
    }

    @Override
    protected void initArgs(Bundle arguments) {

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_order_content;
    }

    @Override
    public void showLoading() {

    }

    @Override
    protected OrderContract.Presenter initPresenter() {
        return new OrderPresenter(this);
    }

    /**
     * 初始化控件
     */
    private void initList() {
        RecyclerScrollListener scrollListener = new RecyclerScrollListener();
        contentList = new ArrayList<>();
        adapter = new OrderContentListAdapter(getActivity(), contentList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        //Item点击事件
        adapter.setOnItemClickedListener(new RecyclerFooterAdapter.OnItemClicked<OrderModel>() {
            @Override
            public void onItemClicked(OrderModel orderModel, RecyclerFooterAdapter.ViewHolder holder) {
                OrderDetailActivity.start(getContext(), orderModel.getOrderId(), orderModel.getStatus());
            }
        });
        //滑动监听
        recyclerView.setOnScrollListener(scrollListener);
        scrollListener.setScrolledToLastListener(new RecyclerScrollListener.OnScrolledToLast() {
            @Override
            public void onScrolledToLast(int position) {
                if (!isLastPage && !refreshLayout.isRefreshing()) {
                    requestDataList();
                }
            }
        });
    }

    /**
     * 发送网络请求
     */
    private void requestDataList() {
        switch (pagerType) {
            case PAGER_ALL:
                mPresenter.loadAllOrder(page, rows);
                break;
            case PAGER_NO_PAYMENT:
                mPresenter.loadNoPaymentOrder(page, rows);
                break;
            case PAGER_NO_PICK_UP:
                mPresenter.loadNoPickOder(page, rows);
                break;
            case PAGER_COMPLETE:
                mPresenter.loadCompleteOrder(page, rows);
                break;
            default:
                ToastUtil.showToast(R.string.toast_logic_error);
                break;
        }
    }

    /**
     * 网络请求数据加载成功时回调
     */
    @Override
    public void loadOrderListSuccess(List<OrderModel> orderModelList, boolean isLastPage) {
        if (orderModelList != null && !orderModelList.isEmpty()) {
            if (page == 1) {
                adapter.setData(orderModelList);
            } else {
                adapter.addAll(orderModelList);
            }
        } else {
            adapter.setToRefresh(false);
        }
        this.isLastPage = isLastPage;
        if (isLastPage) {
            adapter.setToRefresh(false);
        }
        refreshLayout.setRefreshing(false);
    }

    /**
     * 网络请求加载数据失败时
     */
    @Override
    public void loadOrderListFailed() {
        refreshLayout.setRefreshing(false);
        adapter.setToRefresh(false);
    }
}

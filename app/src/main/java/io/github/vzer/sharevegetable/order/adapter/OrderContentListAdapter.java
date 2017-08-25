package io.github.vzer.sharevegetable.order.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import io.github.vzer.common.widget.recycler.RecyclerFooterAdapter;
import io.github.vzer.factory.model.order.OrderModel;
import io.github.vzer.sharevegetable.R;
import io.github.vzer.sharevegetable.order.activity.DiscussActivity;
import io.github.vzer.sharevegetable.widget.DistributeView;

import static android.view.View.GONE;

/**
 * @author YangCihang
 * @since 17/8/1.
 * email yangcihang@hrsoft.net
 */

public class OrderContentListAdapter extends RecyclerFooterAdapter<OrderModel> {
    private FooterItemHolder footerHolder;

    public OrderContentListAdapter(Context context, List<OrderModel> orderModels) {
        super(context, orderModels);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == DATA_ITEM) {
            view = inflater.inflate(R.layout.item_recview_order, parent, false);
            return new ItemHolder(view);
        } else {
            view = inflater.inflate(R.layout.view_rec_footer, parent, false);
            footerHolder = new FooterItemHolder(view);
            return footerHolder;
        }
    }

    public class ItemHolder extends ViewHolder<OrderModel> {
        @BindView(R.id.txt_order_number)
        TextView orderNumberTxt;
        @BindView(R.id.txt_product_list)
        TextView productListTxt;
        @BindView(R.id.view_order_state)
        DistributeView orderStateView;
        @BindView(R.id.txt_product_money)
        TextView moneyTxt;
        @BindView(R.id.btn_action)
        Button actionBtn;
        private OrderModel model;

        ItemHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(OrderModel orderModel) {
            model = orderModel;
            String productList = orderModel.getProductNames().get(0);
            productList = productList + "等" + orderModel.getProductNames().size() + "件商品";
            //订单简介的列表
            productListTxt.setText(productList);
            //订单号
            orderNumberTxt.setText(orderModel.getOrderId());
            //设置钱数
            moneyTxt.setText("￥"+String.valueOf(orderModel.getTotal()));

            switch (model.getStatus()) {
                case OrderModel.STATE_NO_PAYMENT:
                    actionBtn.setText(R.string.text_to_pay);
                    orderStateView.setPaymentState();
                    actionBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // TODO: 17/8/7 订单支付详情页面
                        }
                    });
                    break;
                case OrderModel.STATE_DISTRIBUTE:
                    orderStateView.setPickState();
                    actionBtn.setText(R.string.text_to_pick);
                    actionBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // TODO: 17/8/7 到开锁界面
                        }
                    });
                    break;
                case OrderModel.STATE_FINISH:
                    orderStateView.setFinishState();
                    actionBtn.setText(R.string.text_order_item_evaluate);
                    actionBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // TODO: 17/8/7 到评论界面
                            context.startActivity(new Intent(context,DiscussActivity.class));
                        }
                    });
                    break;
                default:
                   // ToastUtil.showToast(R.string.toast_logic_error);
                    break;
            }
        }
    }

    class FooterItemHolder extends FooterHolder {
        @BindView(R.id.txt_load_more)
        TextView loadTxt;
        @BindView(R.id.progress_footer)
        ProgressBar footerProgress;

        FooterItemHolder(View itemView) {
            super(itemView);
        }
    }

    public void setToRefresh(boolean toRefresh) {
        if (footerHolder != null) {
            if (toRefresh) {
                footerHolder.footerProgress.setVisibility(View.VISIBLE);
                footerHolder.loadTxt.setText("正在加载");
            } else {
                footerHolder.footerProgress.setVisibility(GONE);
                footerHolder.loadTxt.setText("已经加载完啦");
            }
           // notifyItemChanged(footerHolder.getPosition());
        }

    }
}


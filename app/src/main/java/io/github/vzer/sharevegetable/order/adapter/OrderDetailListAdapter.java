package io.github.vzer.sharevegetable.order.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import io.github.vzer.common.widget.recycler.RecyclerViewAdapter;
import io.github.vzer.factory.model.db.Vegetable;
import io.github.vzer.factory.model.order.OrderDetailVegetableModel;
import io.github.vzer.sharevegetable.R;

/**
 * @author YangCihang
 * @since 17/8/3.
 * email yangcihang@hrsoft.net
 */

public class OrderDetailListAdapter extends RecyclerViewAdapter<OrderDetailVegetableModel> {

    public OrderDetailListAdapter(Context context, List<OrderDetailVegetableModel> vegetables) {
        super(context, vegetables);
    }

    @Override
    public ViewHolder<OrderDetailVegetableModel> onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_rec_order_detail, parent, false);
        return new ItemViewHolder(view);
    }

    class ItemViewHolder extends ViewHolder<OrderDetailVegetableModel> {
        @BindView(R.id.txt_vegetable_money)
        TextView moneyTxt;
        @BindView(R.id.txt_vegetable_num)
        TextView numTxt;
        @BindView(R.id.txt_vegetable_name)
        TextView nameTxt;

        public ItemViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(OrderDetailVegetableModel vegetable) {
            moneyTxt.setText("￥"+vegetable.getProductPrice());
            numTxt.setText("x"+vegetable.getStock());
            nameTxt.setText(vegetable.getProductName());
        }
    }
}

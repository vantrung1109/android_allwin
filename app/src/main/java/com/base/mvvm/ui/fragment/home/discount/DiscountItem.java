package com.base.mvvm.ui.fragment.home.discount;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.mvvm.R;
import com.base.mvvm.ui.fragment.home.payment_method.PaymentMethodItem;
import com.base.mvvm.utils.DisplayUtils;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.viewholders.FlexibleViewHolder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscountItem extends AbstractFlexibleItem<DiscountItem.DiscountViewHolder> {

    Integer id;
    Integer imgDiscount;
    Double priceDiscount;
    Double conditionDiscount;
    String timeRemaining;

    public DiscountItem(Integer imgDiscount, Double priceDiscount, Double conditionDiscount, String timeRemaining) {
        this.imgDiscount = imgDiscount;
        this.priceDiscount = priceDiscount;
        this.conditionDiscount = conditionDiscount;
        this.timeRemaining = timeRemaining;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return false;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.rcv_item_discount;
    }

    @Override
    public DiscountViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> flexibleAdapter) {
        return new DiscountViewHolder(view, flexibleAdapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> flexibleAdapter, DiscountViewHolder discountViewHolder, int i, List<Object> list) {
        discountViewHolder.imgDiscouunt.setImageResource(imgDiscount);
        discountViewHolder.tv_price_discount.setText(DisplayUtils.custom_money_discount(priceDiscount));
        discountViewHolder.tv_condition_discount.setText(DisplayUtils.custom_condition_money_discount(conditionDiscount));
        discountViewHolder.tv_time_remaining.setText(timeRemaining);
    }


    public static class DiscountViewHolder extends FlexibleViewHolder {

        ImageView imgDiscouunt;
        TextView tv_price_discount, tv_condition_discount;
        TextView tv_time_remaining;
        Button btn_use_discount;

        public DiscountViewHolder(View view, FlexibleAdapter adapter) {
            super(view, adapter);
            imgDiscouunt = view.findViewById(R.id.img_discount);
            tv_price_discount = view.findViewById(R.id.tv_price_discount);
            tv_condition_discount = view.findViewById(R.id.tv_condition_discount);
            tv_time_remaining = view.findViewById(R.id.tv_time_remaining);
            btn_use_discount = view.findViewById(R.id.btn_use_discount);
        }
    }
}

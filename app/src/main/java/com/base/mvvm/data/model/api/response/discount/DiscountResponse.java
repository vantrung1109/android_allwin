package com.base.mvvm.data.model.api.response.discount;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.mvvm.R;
import com.base.mvvm.data.model.api.response.service.ServiceResponse;
import com.base.mvvm.ui.fragment.home.discount.DiscountItem;
import com.base.mvvm.utils.DisplayUtils;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.viewholders.FlexibleViewHolder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscountResponse extends AbstractFlexibleItem<DiscountResponse.DiscountResponseViewHolder> implements  Serializable {
    private Long id;
    private Integer status;
    private String name;
    private String startDate;
    private String endDate;
    private Integer kind;
    private Integer state;
    private Double discountValue;
    private Double limitValueMin;
    private Integer quantity;
    private List<ServiceResponse> services;
    private Boolean isSelected;

    public static Integer currentPosition;
    @Override
    public int getLayoutRes() {
        return R.layout.rcv_item_discount;
    }

    @Override
    public DiscountResponseViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> adapter) {
        return new DiscountResponseViewHolder(view, adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, DiscountResponseViewHolder holder, int position, List<Object> payloads) {

        // Set textview discount value
        holder.tv_price_discount.setText(DisplayUtils.custom_money_discount(discountValue));

        // Handle textview condition discount (limit value min)
        if (limitValueMin != null)
            holder.tv_condition_discount.setText(DisplayUtils.custom_condition_money_discount(limitValueMin));

        // Handle textview time remaining
        try {
            holder.tv_time_remaining.setText(DisplayUtils.custom_time_remaining(endDate));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        // Handle button use discount and change background color for item view by status
        if (status == 1) {
            holder.btn_use_discount.setEnabled(true);
        } else {
            holder.btn_use_discount.setEnabled(false);
            holder.btn_use_discount.setBackground(holder.btn_use_discount.getContext().getResources().getDrawable(R.drawable.btn_disable, null));
            holder.itemView.setBackground(holder.itemView.getContext().getResources().getDrawable(R.drawable.background_promotion_disable, null));
        }

        // Handle button use discount
        holder.btn_use_discount.setOnClickListener(v -> {
            for (IFlexible item : adapter.getCurrentItems()) {
                ((DiscountResponse)item).setIsSelected(false);
            }
            // Set the item selected
            this.setIsSelected(true);
            adapter.notifyDataSetChanged();
            currentPosition = position;
        });

        // Handle item selected
        if (isSelected != null && isSelected) {
            holder.itemView.setBackground(holder.itemView.getContext().getResources().getDrawable(R.drawable.background_vehicle_focus, null));
            holder.btn_use_discount.setText(R.string.apply_discount);
            holder.btn_use_discount.setEnabled(false);
        } else if (status == 1 &&
                (isSelected == null
                || (isSelected != null && !isSelected))) {
            holder.itemView.setBackground(holder.itemView.getContext().getResources().getDrawable(R.drawable.background_vehicle_normal, null));
            holder.btn_use_discount.setText(R.string.str_use_discount);
            holder.btn_use_discount.setEnabled(true);
        }
    }

    public static Integer getCurrentPosition() {
        return currentPosition;
    }

    public static class DiscountResponseViewHolder extends FlexibleViewHolder {

        ImageView imgDiscouunt;
        TextView tv_price_discount, tv_condition_discount;
        TextView tv_time_remaining;
        TextView btn_use_discount;

        public DiscountResponseViewHolder(View view, FlexibleAdapter adapter) {
            super(view, adapter);
            imgDiscouunt = view.findViewById(R.id.img_discount);
            tv_price_discount = view.findViewById(R.id.tv_price_discount);
            tv_condition_discount = view.findViewById(R.id.tv_condition_discount);
            tv_time_remaining = view.findViewById(R.id.tv_time_remaining);
            btn_use_discount = view.findViewById(R.id.btn_use_discount);
        }
    }
}


package com.base.mvvm.data.model.api.response.service;

import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.mvvm.R;
import com.base.mvvm.utils.DisplayUtils;

import java.io.Serializable;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.viewholders.FlexibleViewHolder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponse extends AbstractFlexibleItem<ServiceResponse.ServiceViewHolder>
        implements Serializable {
    private CategoryResponse category;
    private String description;
    private Long id;
    private String image;
    private Integer kind;
    private String name;
    private String price;
    private String promotionPrice;
    private String size;
    private String weight;


    public static View firstView;
    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.rcv_item_service;
    }

    @Override
    public ServiceViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> flexibleAdapter) {
        return new ServiceViewHolder(view, flexibleAdapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> flexibleAdapter, ServiceViewHolder serviceViewHolder, int i, List<Object> list) {

        // set position 0 is the item selected
        if (i == 0) {
            firstView = serviceViewHolder.itemView;
            firstView.setBackground(serviceViewHolder.itemView.getContext().getResources().getDrawable(R.drawable.background_vehicle_focus, null));
        }

        // Handle tv that represents name of service
        serviceViewHolder.tvNameVehicle.setText(name);

        // Handle tv that represents promotion price
        if (promotionPrice != null){
            serviceViewHolder.tvPromotionMoney.setPaintFlags(serviceViewHolder.tvPromotionMoney.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            serviceViewHolder.tvPromotionMoney.setText(DisplayUtils.custom_money(Double.parseDouble(promotionPrice)));
            serviceViewHolder.tvPromotionMoney.setVisibility(View.VISIBLE);
        } else
            serviceViewHolder.tvPromotionMoney.setVisibility(View.GONE);

        // Handle tv that represents price
        if (promotionPrice != null)
            serviceViewHolder.tvMoney.setText(DisplayUtils.custom_money(Double.parseDouble(price) - Double.parseDouble(promotionPrice)));
        else
            serviceViewHolder.tvMoney.setText(DisplayUtils.custom_money(Double.parseDouble(price)));

    }

    public static class ServiceViewHolder extends FlexibleViewHolder {
        ImageView imgVehicle;
        TextView tvNameVehicle, tvMoney, tvPromotionMoney;

        public ServiceViewHolder(View view, FlexibleAdapter adapter) {
            super(view, adapter);
            imgVehicle = view.findViewById(R.id.img_vehicle);
            tvNameVehicle = view.findViewById(R.id.tv_name_vehicle);
            tvMoney = view.findViewById(R.id.tv_price);
            tvPromotionMoney = view.findViewById(R.id.tv_promotion_price);
        }
    }
}

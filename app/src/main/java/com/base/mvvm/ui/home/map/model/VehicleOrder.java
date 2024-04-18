package com.base.mvvm.ui.home.map.model;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.mvvm.R;
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
public class VehicleOrder extends AbstractFlexibleItem<VehicleOrder.VehicleOrderViewHolder> {

    private int imgVehicle;
    private String vehicleName;
    private double price;

    @Override
    public boolean equals(Object o) {
        if (o instanceof VehicleOrder) {
            VehicleOrder inItem = (VehicleOrder) o;
            return inItem.equals(inItem);
        }
        return false;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.rcv_item_vehicle_price;
    }

    @Override
    public VehicleOrderViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> flexibleAdapter) {
        return new VehicleOrderViewHolder(view, flexibleAdapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> flexibleAdapter, VehicleOrderViewHolder vehicleOrderViewHolder, int i, List<Object> list) {
        vehicleOrderViewHolder.imgVehicle.setImageResource(imgVehicle);
        vehicleOrderViewHolder.tvVehicleName.setText(vehicleName);
        vehicleOrderViewHolder.tvPrice.setText(DisplayUtils.custom_money(price));
    }

    public static class VehicleOrderViewHolder extends FlexibleViewHolder {
        ImageView imgVehicle;
        TextView tvVehicleName, tvPrice;

        public VehicleOrderViewHolder(View view, FlexibleAdapter adapter) {
            super(view, adapter);
            imgVehicle = view.findViewById(R.id.img_vehicle);
            tvVehicleName = view.findViewById(R.id.tv_name_vehicle);
            tvPrice = view.findViewById(R.id.tv_price);
        }
    }
}

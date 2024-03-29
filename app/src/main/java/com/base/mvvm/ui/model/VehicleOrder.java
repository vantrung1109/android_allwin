package com.base.mvvm.ui.model;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.mvvm.R;

import java.util.Calendar;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.viewholders.FlexibleViewHolder;
import lombok.Data;

@Data
public class VehicleOrder extends AbstractFlexibleItem<VehicleOrder.VehicleOrderViewholder>{

    int id;
    String address;
    String address_detail;
    long price;
    Boolean status;
    String date;
    int resourceId;

    public VehicleOrder(String address, String address_detail, long price, Boolean status, String date, int resourceId) {
        this.address = address;
        this.address_detail = address_detail;
        this.price = price;
        this.status = status;
        this.date = date;
        this.resourceId = resourceId;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.rcv_vehicle_order_item;
    }

    @Override
    public VehicleOrderViewholder createViewHolder(View view, FlexibleAdapter<IFlexible> flexibleAdapter) {
        return new VehicleOrderViewholder(view, flexibleAdapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> flexibleAdapter, VehicleOrderViewholder vehicleOrderViewholder, int i, List<Object> list) {
        vehicleOrderViewholder.address.setText(address);
        vehicleOrderViewholder.address_detail.setText(address_detail);
        vehicleOrderViewholder.price.setText(String.valueOf(price) + "đ");
        vehicleOrderViewholder.status.setText(status ? "Hoàn thành" : "Đã hủy");
        vehicleOrderViewholder.date.setText(date);
        vehicleOrderViewholder.image.setImageResource(resourceId);
    }

    public static class VehicleOrderViewholder extends FlexibleViewHolder {
        TextView address, address_detail, price, status, date;
        ImageView image;
        public VehicleOrderViewholder(View view, FlexibleAdapter adapter) {
            super(view, adapter);
            address = view.findViewById(R.id.tv_address);
            address_detail = view.findViewById(R.id.tv_address_detail);
            price = view.findViewById(R.id.tv_price);
            status = view.findViewById(R.id.tv_status);
            date = view.findViewById(R.id.tv_date);
            image = view.findViewById(R.id.img_vehicle);
        }
    }
}

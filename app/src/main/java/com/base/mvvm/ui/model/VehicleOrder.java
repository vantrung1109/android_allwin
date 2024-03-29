package com.base.mvvm.ui.model;

import android.view.View;

import java.util.Calendar;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.viewholders.FlexibleViewHolder;

public class VehicleOrder extends AbstractFlexibleItem<VehicleOrder.VehicleOrderViewholder>{

    int id;
    String address;
    String address_detail;
    long price;
    Boolean status;
    Calendar date;

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int getLayoutRes() {
        return 0;
    }

    @Override
    public VehicleOrderViewholder createViewHolder(View view, FlexibleAdapter<IFlexible> flexibleAdapter) {
        return null;
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> flexibleAdapter, VehicleOrderViewholder vehicleOrderViewholder, int i, List<Object> list) {

    }

    public static class VehicleOrderViewholder extends FlexibleViewHolder {
        public VehicleOrderViewholder(View view, FlexibleAdapter adapter) {
            super(view, adapter);
        }
    }
}

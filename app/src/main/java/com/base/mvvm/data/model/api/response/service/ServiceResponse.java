package com.base.mvvm.data.model.api.response.service;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.mvvm.R;
import com.base.mvvm.utils.DisplayUtils;

import org.w3c.dom.Text;

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
    private String size;
    private String weight;

    @Override
    public int getLayoutRes() {
        return R.layout.rcv_item_vehicle_price;
    }

    @Override
    public ServiceViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> flexibleAdapter) {
        return new ServiceViewHolder(view, flexibleAdapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> flexibleAdapter, ServiceViewHolder serviceViewHolder, int i, List<Object> list) {
        serviceViewHolder.tvNameVehicle.setText(name);
        serviceViewHolder.tvMoney.setText(DisplayUtils.custom_money(Double.parseDouble(price)));

    }

    public static class ServiceViewHolder extends FlexibleViewHolder {
        ImageView imgVehicle;
        TextView tvNameVehicle, tvMoney;

        public ServiceViewHolder(View view, FlexibleAdapter adapter) {
            super(view, adapter);
            imgVehicle = view.findViewById(R.id.img_vehicle);
            tvNameVehicle = view.findViewById(R.id.tv_name_vehicle);
            tvMoney = view.findViewById(R.id.tv_price);
        }
    }
}

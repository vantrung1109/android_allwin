package com.base.mvvm.ui.fragment.home.model;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.mvvm.R;

import java.util.List;
import java.util.Objects;

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
public class AddressSaveItem extends AbstractFlexibleItem<AddressSaveItem.AddressSaveItemViewHolder> {
    private Long id;
    private int resourceImg;
    private String pickupAddress;
    private String destinationAddress;


    public AddressSaveItem(int resourceImg, String pickupAddress, String destinationAddress) {
        this.resourceImg = resourceImg;
        this.pickupAddress = pickupAddress;
        this.destinationAddress = destinationAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddressSaveItem)) return false;
        return false;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.rcv_item_address_save;
    }

    @Override
    public AddressSaveItemViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> flexibleAdapter) {
        return new AddressSaveItemViewHolder(view, flexibleAdapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> flexibleAdapter, AddressSaveItemViewHolder addressSaveItemViewHolder, int i, List<Object> list) {
        addressSaveItemViewHolder.img.setImageResource(resourceImg);
        addressSaveItemViewHolder.tvPickUpAddress.setText(pickupAddress);
        addressSaveItemViewHolder.tvDestinationAddress.setText(destinationAddress);
    }

    public static class AddressSaveItemViewHolder extends FlexibleViewHolder {
        ImageView img;
        TextView tvPickUpAddress, tvDestinationAddress;
        public AddressSaveItemViewHolder(View view, FlexibleAdapter adapter) {
            super(view, adapter);
            img = view.findViewById(R.id.img_title_address_save);
            tvPickUpAddress = view.findViewById(R.id.tv_pickup_address);
            tvDestinationAddress = view.findViewById(R.id.tv_destination_address);
        }
    }


}

package com.base.mvvm.ui.fragment.home.cancel_trip;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.mvvm.R;

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
public class CancelTripItem extends AbstractFlexibleItem<CancelTripItem.CancelTripViewHolder> {

    String id;
    String reason;
    int imgRes;

    public CancelTripItem(String reason, int imgRes) {
        this.reason = reason;
        this.imgRes = imgRes;
    }

    @Override
    public boolean equals(Object o) {
        return this == o;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.rcv_cancel_trip_item;
    }

    @Override
    public CancelTripViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> flexibleAdapter) {
        return new CancelTripViewHolder(view, flexibleAdapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> flexibleAdapter, CancelTripViewHolder cancelTripViewHolder, int i, List<Object> list) {
        cancelTripViewHolder.tv_reason.setText(reason);
        cancelTripViewHolder.img_radiobutton.setImageResource(imgRes);

    }

    public static class CancelTripViewHolder extends FlexibleViewHolder {

        TextView tv_reason;
        ImageView img_radiobutton;

        public CancelTripViewHolder(View view, FlexibleAdapter adapter) {
            super(view, adapter);
            tv_reason = view.findViewById(R.id.tv_reason);
            img_radiobutton = view.findViewById(R.id.img_radiobutton);
        }
    }
}

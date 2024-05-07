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
public class CancelRatingItem extends AbstractFlexibleItem<CancelRatingItem.CancelRatingViewHolder> {

    String id;
    String status;
    int imgRes;

    public CancelRatingItem(String reason, int imgRes) {
        this.status = reason;
        this.imgRes = imgRes;
    }

    @Override
    public boolean equals(Object o) {
        return this == o;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.rcv_cancel_rating_item;
    }

    @Override
    public CancelRatingViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> flexibleAdapter) {
        return new CancelRatingViewHolder(view, flexibleAdapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> flexibleAdapter, CancelRatingViewHolder cancelTripViewHolder, int i, List<Object> list) {
        cancelTripViewHolder.tv_reason.setText(status);
        cancelTripViewHolder.img_radiobutton.setImageResource(imgRes);

    }

    public static class CancelRatingViewHolder extends FlexibleViewHolder {

        TextView tv_reason;
        ImageView img_radiobutton;

        public CancelRatingViewHolder(View view, FlexibleAdapter adapter) {
            super(view, adapter);
            tv_reason = view.findViewById(R.id.tv_reason);
            img_radiobutton = view.findViewById(R.id.img_radiobutton);
        }
    }
}

package com.base.mvvm.data.model.api.api_search;

import android.view.View;
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
@AllArgsConstructor
@NoArgsConstructor
public class Prediction extends AbstractFlexibleItem<Prediction.PredictionViewHolder> {
    private String description;
    private List<MatchedSubString> matched_substrings;
    private String place_id;
    private String reference;
    private StructuredFormatting structured_formatting;
    private List<Terms> terms;
    private List<String> types;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Prediction)) return false;
        Prediction prediction = (Prediction) o;
        return description.equals(prediction.description);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.rcv_item_address_save;
    }

    @Override
    public PredictionViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> flexibleAdapter) {
        return new PredictionViewHolder(view, flexibleAdapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> flexibleAdapter, PredictionViewHolder predictionViewHolder, int i, List<Object> list) {
        predictionViewHolder.tv_description.setText(description);
    }


    public static class PredictionViewHolder extends FlexibleViewHolder {
        TextView tv_description;
        public PredictionViewHolder(View view, FlexibleAdapter adapter) {
            super(view, adapter);
            tv_description = view.findViewById(R.id.tv_description);
        }
    }

}
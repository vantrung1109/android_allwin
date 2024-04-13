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
public class SearchPlaceApi {
    private List<Prediction> predictions;
    private String status;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof SearchPlaceApi)) return false;
//        SearchPlaceApi searchPlaceApi = (SearchPlaceApi) o;
//        return predictions.equals(searchPlaceApi.predictions);
//    }
//
//    @Override
//    public int getLayoutRes() {
//        return R.layout.rcv_item_address_save;
//    }
//
//    @Override
//    public SearchPlaceApiViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> flexibleAdapter) {
//        return new SearchPlaceApiViewHolder(view, flexibleAdapter);
//    }
//
//    @Override
//    public void bindViewHolder(FlexibleAdapter<IFlexible> flexibleAdapter, SearchPlaceApiViewHolder searchPlaceApiViewHolder, int i, List<Object> list) {
//        searchPlaceApiViewHolder.tv_description.setText(predictions.get(i).getDescription());
//    }
//
//    public static class SearchPlaceApiViewHolder extends FlexibleViewHolder {
//        TextView tv_description;
//        public SearchPlaceApiViewHolder(View view, FlexibleAdapter adapter) {
//            super(view, adapter);
//            tv_description = view.findViewById(R.id.tv_description);
//        }
//    }
}

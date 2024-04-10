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
public class TitleAddressSave extends AbstractFlexibleItem<TitleAddressSave.TitleAddressSaveViewHolder>
{
    private Long id;
    private int resourceImg;
    private String title;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TitleAddressSave)) return false;

        TitleAddressSave that = (TitleAddressSave) o;

        if (id != that.id) return false;
        if (resourceImg != that.resourceImg) return false;
        return Objects.equals(title, that.title);
    }

    public TitleAddressSave(int resourceImg, String title) {
        this.resourceImg = resourceImg;
        this.title = title;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.rcv_tittle_address_save;
    }

    @Override
    public TitleAddressSaveViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> flexibleAdapter) {
        return new TitleAddressSaveViewHolder(view, flexibleAdapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> flexibleAdapter, TitleAddressSaveViewHolder titleAddressSaveViewHolder, int i, List<Object> list) {
        titleAddressSaveViewHolder.img.setImageResource(resourceImg);
        titleAddressSaveViewHolder.tv_title.setText(title);
    }

    public static class TitleAddressSaveViewHolder extends FlexibleViewHolder{
        ImageView img;
        TextView tv_title;
        public TitleAddressSaveViewHolder(View view, FlexibleAdapter adapter) {
            super(view, adapter);
            img = view.findViewById(R.id.img_title_address_save);
            tv_title = view.findViewById(R.id.tv_title_address_save);
        }
    }

}

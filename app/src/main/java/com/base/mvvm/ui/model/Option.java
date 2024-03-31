package com.base.mvvm.ui.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.base.mvvm.R;

import java.io.Serializable;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.viewholders.FlexibleViewHolder;
import lombok.Data;

@Data
public class Option extends AbstractFlexibleItem<Option.OptionViewHolder> implements Parcelable, Serializable {

    int id;
    String title;
    private int selectedItem = -1;

    public Option(String title) {
        this.title = title;
    }

    protected Option(Parcel in) {
        id = in.readInt();
        title = in.readString();
    }

    public static final Creator<Option> CREATOR = new Creator<Option>() {
        @Override
        public Option createFromParcel(Parcel in) {
            return new Option(in);
        }

        @Override
        public Option[] newArray(int size) {
            return new Option[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (o instanceof Option) {
            Option inItem = (Option) o;
            return this.id == inItem.id;
        }
        return false;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.rcv_option_item;
    }

    @Override
    public OptionViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> flexibleAdapter) {
        return new OptionViewHolder(view, flexibleAdapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> flexibleAdapter, OptionViewHolder optionViewHolder, int i, List<Object> list) {
        optionViewHolder.title.setText(title);
        optionViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedItem = i;
                optionViewHolder.cardView.setCardBackgroundColor(optionViewHolder.itemView.getResources().getColor(R.color.background_option, null));
                optionViewHolder.title.setTextColor(optionViewHolder.itemView.getResources().getColor(R.color.white, null));
            }
        });
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
    }

    public static class OptionViewHolder extends FlexibleViewHolder {
        TextView title;
        CardView cardView;

        public OptionViewHolder(View view, FlexibleAdapter adapter) {
            super(view, adapter);
            title = view.findViewById(R.id.tv_title);
            cardView = view.findViewById(R.id.cart_view);


        }

    }


}

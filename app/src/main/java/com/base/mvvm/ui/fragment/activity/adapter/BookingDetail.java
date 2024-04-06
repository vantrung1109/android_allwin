package com.base.mvvm.ui.fragment.activity.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.mvvm.R;
import com.base.mvvm.databinding.RcvBookingDetailItemBinding;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.viewholders.FlexibleViewHolder;


public class BookingDetail extends AbstractFlexibleItem<BookingDetail.BookingDetailViewHolder> {

    int id;
    String address;
    String address_detail;
    long price;
    Boolean status;
    String date;
    int resourceId;

    public BookingDetail(String address, String address_detail, long price, Boolean status, String date, int resourceId) {
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
        return R.layout.rcv_booking_detail_item;
    }

    @Override
    public BookingDetailViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> flexibleAdapter) {
        return new BookingDetailViewHolder(view, flexibleAdapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> flexibleAdapter, BookingDetailViewHolder bookingDetailViewHolder, int i, List<Object> list) {
        bookingDetailViewHolder.address.setText(address);
        bookingDetailViewHolder.address_detail.setText(address_detail);
        bookingDetailViewHolder.price.setText(String.valueOf(price) + "đ");
        bookingDetailViewHolder.status.setText(status ? "Hoàn thành" : "Đã hủy");
        bookingDetailViewHolder.date.setText(date);
        bookingDetailViewHolder.image.setImageResource(resourceId);
    }

    public static class BookingDetailViewHolder extends FlexibleViewHolder {

        RcvBookingDetailItemBinding binding;
        TextView address, address_detail, price, status, date;
        ImageView image;
        public BookingDetailViewHolder(View view, FlexibleAdapter adapter) {
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

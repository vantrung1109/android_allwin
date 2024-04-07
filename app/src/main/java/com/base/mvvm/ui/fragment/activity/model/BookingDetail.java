package com.base.mvvm.ui.fragment.activity.model;

import android.view.LayoutInflater;
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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDetail extends AbstractFlexibleItem<BookingDetail.BookingDetailViewHolder> {

    Long id;
    String createDate;
    String pickupAddress;
    String destinationaAddress;
    Double money;
    Integer state;

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
//        RcvBookingDetailItemBinding rcvBookingDetailItemBinding = RcvBookingDetailItemBinding.inflate(LayoutInflater.from(view.getContext()));
        return new BookingDetailViewHolder(view, flexibleAdapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> flexibleAdapter, BookingDetailViewHolder bookingDetailViewHolder, int i, List<Object> list) {
        bookingDetailViewHolder.tvCreateDate.setText(createDate);
        bookingDetailViewHolder.tvPickupAddress.setText(pickupAddress);
        bookingDetailViewHolder.tvDestinationAddress.setText(destinationaAddress);
        bookingDetailViewHolder.tvMoney.setText(custom_money(money));
        if (state == 300){
            bookingDetailViewHolder.tvState.setText(bookingDetailViewHolder.itemView.getContext().getResources().getString(R.string.str_completed_booking));
            bookingDetailViewHolder.tvState.setTextColor(bookingDetailViewHolder.itemView.getContext().getResources().getColor(R.color.completed_booking, null));
        }
        else if (state == -100){
            bookingDetailViewHolder.tvState.setText("• Hủy");
            bookingDetailViewHolder.tvState.setTextColor(bookingDetailViewHolder.itemView.getContext().getResources().getColor(R.color.red, null));
        } else {
            bookingDetailViewHolder.tvState.setText("• Đang xử lý");
            bookingDetailViewHolder.tvState.setTextColor(bookingDetailViewHolder.itemView.getContext().getResources().getColor(R.color.processing_booking, null));
        }



    }

    public static class BookingDetailViewHolder extends FlexibleViewHolder {

//        RcvBookingDetailItemBinding rcvBookingDetailItemBinding;
        TextView tvCreateDate, tvPickupAddress, tvDestinationAddress, tvMoney, tvState;
        ImageView imgVehicle;
        public BookingDetailViewHolder(View view, FlexibleAdapter adapter) {
            super(view, adapter);
            tvCreateDate = view.findViewById(R.id.tv_create_date);
            tvPickupAddress = view.findViewById(R.id.tv_pickup_address);
            tvDestinationAddress = view.findViewById(R.id.tv_destination_address);
            tvMoney = view.findViewById(R.id.tv_money);
            tvState = view.findViewById(R.id.tv_state);
            imgVehicle = view.findViewById(R.id.img_vehicle);
        }
    }

    public String custom_money(Double money) {
        int custom_money = money.intValue();
        String custom_money_string = "";
        while (custom_money / 1000 != 0){
            int temp =  custom_money % 1000;
            if (temp < 10){
                custom_money_string = ".00" + String.valueOf(temp) + custom_money_string;
            } else if (temp < 100){
                custom_money_string = ".0" + String.valueOf(temp) + custom_money_string;
            } else {
                custom_money_string = "." + String.valueOf(temp) + custom_money_string;
            }
            custom_money = custom_money / 1000;
        }
        custom_money_string = String.valueOf(custom_money) + custom_money_string + " đ";
        return custom_money_string;
    }
}

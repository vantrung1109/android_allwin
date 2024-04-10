package com.base.mvvm.data.model.api.response.booking;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.mvvm.R;
import com.base.mvvm.data.model.api.response.customer.CustomerProfileResponse;
import com.base.mvvm.data.model.api.response.driver.DriverProfileResponse;
import com.base.mvvm.data.model.api.response.driver_vehicle.DriverVehicle;
import com.base.mvvm.data.model.api.response.rating.Rating;
import com.base.mvvm.data.model.api.response.room.Room;
import com.base.mvvm.data.model.api.response.service.ServiceResponse;

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
public class MyBookingResponse extends AbstractFlexibleItem<MyBookingResponse.MyBookingViewHolder>
    implements Serializable
{
    private Long id;
    private Double averageRating;
    private List<BookingDetail> bookingDetails;
    private Integer codPrice;
    private String code;
    private String consigneeName;
    private String consigneePhone;
    private String createdDate;
    private CustomerProfileResponse customer;
    private String customerNote;
    private String deliveryImage;
    private String destinationAddress;
    private Double distance;
    private DriverProfileResponse driver;
    private DriverVehicle driverVehicle;
    private String idCod;
    private Double money;
    private String pickupAddress;
    private String pickupImage;
    private Double promotionMoney;
    private Rating rating;
    private Double ratioShare;
    private Room room;
    private String senderName;
    private String senderPhone;
    private ServiceResponse service;
    private Integer state;
    private String status;

    public MyBookingResponse() {
        this.customer = new CustomerProfileResponse();
        this.driver = new DriverProfileResponse();
        this.driverVehicle = new DriverVehicle();
        this.rating = new Rating();
        this.room = new Room();
        this.service = new ServiceResponse();

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyBookingResponse)) return false;
        MyBookingResponse that = (MyBookingResponse) o;
        return getId().equals(that.getId());
    }

    @Override
    public int getLayoutRes() {
        return R.layout.rcv_booking_detail_item;
    }

    @Override
    public MyBookingViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> flexibleAdapter) {
        return new MyBookingViewHolder(view, flexibleAdapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> flexibleAdapter, MyBookingViewHolder myBookingViewHolder, int i, List<Object> list) {
        myBookingViewHolder.tvCreateDate.setText(createdDate);
        myBookingViewHolder.tvPickupAddress.setText(pickupAddress);
        myBookingViewHolder.tvDestinationAddress.setText(destinationAddress);
        myBookingViewHolder.tvMoney.setText(custom_money(money));
        if (state == 300){
            myBookingViewHolder.tvState.setText(myBookingViewHolder.itemView.getContext().getResources().getString(R.string.str_completed_booking));
            myBookingViewHolder.tvState.setTextColor(myBookingViewHolder.itemView.getContext().getResources().getColor(R.color.completed_booking, null));
        }
        else if (state == -100){
            myBookingViewHolder.tvState.setText("• Hủy");
            myBookingViewHolder.tvState.setTextColor(myBookingViewHolder.itemView.getContext().getResources().getColor(R.color.red, null));
        } else {
            myBookingViewHolder.tvState.setText("• Đang xử lý");
            myBookingViewHolder.tvState.setTextColor(myBookingViewHolder.itemView.getContext().getResources().getColor(R.color.processing_booking, null));
        }
    }



    public static class MyBookingViewHolder extends FlexibleViewHolder {

        //        RcvBookingDetailItemBinding rcvBookingDetailItemBinding;
        TextView tvCreateDate, tvPickupAddress, tvDestinationAddress, tvMoney, tvState;
        ImageView imgVehicle;
        public MyBookingViewHolder(View view, FlexibleAdapter adapter) {
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
        custom_money_string = String.valueOf(custom_money) + custom_money_string + "đ";
        return custom_money_string;
    }
}

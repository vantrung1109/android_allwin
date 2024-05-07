package com.base.mvvm.data.service;

import com.base.mvvm.R;

import com.base.mvvm.ui.fragment.home.cancel_trip.CancelRatingItem;
import com.base.mvvm.ui.fragment.home.cancel_trip.CancelTripItem;
import com.base.mvvm.ui.fragment.home.discount.DiscountItem;
import com.base.mvvm.ui.fragment.home.model.TitleAddressSave;
import com.base.mvvm.ui.fragment.home.payment_method.PaymentMethodItem;
import com.base.mvvm.ui.fragment.home.model.VehicleOrder;

import java.util.ArrayList;
import java.util.List;

import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;

public class DatabaseService {
    private static final String TAG = DatabaseService.class.getSimpleName();
    private static DatabaseService mInstance;

    private List<AbstractFlexibleItem> mItems = new ArrayList<AbstractFlexibleItem>();

    private DatabaseService() {
    }

    public static DatabaseService getInstance() {
        if (mInstance == null) {
            mInstance = new DatabaseService();
        }
        return mInstance;
    }

    public void clear() {
        mItems.clear();
    }

    public List<AbstractFlexibleItem> getDatabaseList() {
        return mItems;
    }
    public List<AbstractFlexibleItem> getTitleAddressSave() {
        List<AbstractFlexibleItem>  items = new ArrayList<>();
        items.add(new TitleAddressSave(R.drawable.private_home, "Nhà riêng"));
        items.add(new TitleAddressSave(R.drawable.company, "Công ty"));
        items.add(new TitleAddressSave(R.drawable.add_location, "Thêm địa điểm"));
        return items;
    }

    public List<AbstractFlexibleItem> getVehicleOrders() {
        List<AbstractFlexibleItem>  items = new ArrayList<>();
        items.add(new VehicleOrder(R.drawable.motorbike, "WinBike", 50000));
        items.add(new VehicleOrder(R.drawable.car, "WinCar", 100000));
        items.add(new VehicleOrder(R.drawable.car_high, "Winwin High Class", 250000));
        items.add(new VehicleOrder(R.drawable.motorbike, "WinBike", 50000));
        items.add(new VehicleOrder(R.drawable.car, "WinCar", 100000));
        items.add(new VehicleOrder(R.drawable.car_high, "Winwin High Class", 250000));
        items.add(new VehicleOrder(R.drawable.motorbike, "WinBike", 50000));
        items.add(new VehicleOrder(R.drawable.car, "WinCar", 100000));
        items.add(new VehicleOrder(R.drawable.car_high, "Winwin High Class", 250000));
        items.add(new VehicleOrder(R.drawable.motorbike, "WinBike", 50000));
        items.add(new VehicleOrder(R.drawable.car, "WinCar", 100000));
        items.add(new VehicleOrder(R.drawable.car_high, "Winwin High Class", 250000));
        return items;
    }

    public List<AbstractFlexibleItem> getPaymentMethods() {
        List<AbstractFlexibleItem>  items = new ArrayList<>();
        items.add(new PaymentMethodItem(R.drawable.cash, "Tiền mặt", true));
        items.add(new PaymentMethodItem(R.drawable.card_money, "Thẻ tín dụng/ Thẻ ghi nợ", false));
        return items;
    }

    public List<AbstractFlexibleItem> getDiscountItems() {
        List<AbstractFlexibleItem>  items = new ArrayList<>();
        items.add(new DiscountItem(R.drawable.car_discount, 20000.0, 50000.0, "15:00, 20/9/2023"));
        items.add(new DiscountItem(R.drawable.car_discount, 20000.0, 50000.0, "15:00, 20/9/2023"));
        items.add(new DiscountItem(R.drawable.car_discount, 20000.0, 50000.0, "15:00, 20/9/2023"));
        items.add(new DiscountItem(R.drawable.car_discount, 20000.0, 50000.0, "15:00, 20/9/2023"));
        items.add(new DiscountItem(R.drawable.car_discount, 20000.0, 50000.0, "15:00, 20/9/2023"));
        return items;
    }

    public List<AbstractFlexibleItem> getCancelItems() {
        List<AbstractFlexibleItem>  items = new ArrayList<>();
        items.add(new CancelTripItem("Tôi nhập sai địa chỉ", R.drawable.radiobutton_normal));
        items.add(new CancelTripItem("Tìm được chỗ gần hơn", R.drawable.radiobutton_normal));
        items.add(new CancelTripItem("Chưa áp mã giảm giá", R.drawable.radiobutton_normal));
        return items;
    }
    public List<AbstractFlexibleItem> getCancelRatingItems() {
        List<AbstractFlexibleItem>  items = new ArrayList<>();
        items.add(new CancelRatingItem("Tuyệt vời", R.drawable.radiobutton_normal));
        items.add(new CancelRatingItem("Tạm ổn", R.drawable.radiobutton_normal));
        items.add(new CancelRatingItem("Quá tệ", R.drawable.radiobutton_normal));
        return items;
    }

}
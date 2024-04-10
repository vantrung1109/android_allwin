package com.base.mvvm.data.service;

import com.base.mvvm.R;

import com.base.mvvm.ui.fragment.home.model.AddressSaveItem;
import com.base.mvvm.ui.fragment.home.model.TitleAddressSave;

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

    public List<AbstractFlexibleItem> getAddressSaveItems() {
        List<AbstractFlexibleItem>  items = new ArrayList<>();
        items.add(new AddressSaveItem(R.drawable.address_save_default_img, "Masteri Thảo Điền", "Thảo Điền, Thủ Đức, Hồ Chí Minh"));
        items.add(new AddressSaveItem(R.drawable.address_save_default_img, "Masteri Thảo Điền", "Thảo Điền, Thủ Đức, Hồ Chí Minh"));
        items.add(new AddressSaveItem(R.drawable.address_save_default_img, "Masteri Thảo Điền", "Thảo Điền, Thủ Đức, Hồ Chí Minh"));
        return items;
    }


}
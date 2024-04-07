package com.base.mvvm.data.service;

import com.base.mvvm.R;
import com.base.mvvm.ui.fragment.activity.model.Option;
import com.base.mvvm.ui.fragment.activity.model.BookingDetail;

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
    public List<AbstractFlexibleItem> getOptionsList() {
        List<AbstractFlexibleItem>  items = new ArrayList<>();
        items.add(new Option("Đặt xe"));
        items.add(new Option("Giao hàng"));
        items.add(new Option("Đi chợ"));
        return items;
    }



}
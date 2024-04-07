package com.base.mvvm.ui.fragment.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.base.mvvm.BR;
import com.base.mvvm.R;

import com.base.mvvm.data.model.api.response.booking.MyBookingResponse;
import com.base.mvvm.data.service.DatabaseService;
import com.base.mvvm.databinding.FragmentActivityBinding;
import com.base.mvvm.di.component.FragmentComponent;
import com.base.mvvm.ui.base.BaseFragment;
import com.base.mvvm.ui.fragment.activity.model.BookingDetail;
import com.base.mvvm.ui.fragment.activity.model.Option;

import java.util.ArrayList;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.IFlexible;


public class ActivityFragment extends BaseFragment<FragmentActivityBinding, ActivityFragmentViewModel>

{
    FlexibleAdapter mFlexibleAdapterBookingDetail;
    @Override
    public int getBindingVariable() {
        return BR.vm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_activity;
    }

    @Override
    protected void performDataBinding() {



        MutableLiveData<List<BookingDetail>> liveDataListBooking = new MutableLiveData<>();
        List<BookingDetail> listBookingDetails = new ArrayList<>();

        viewModel.listMyBookings.observe(getViewLifecycleOwner(), myBookingResponses -> {
            if (myBookingResponses != null) {
                for (MyBookingResponse booking : myBookingResponses) {
                    listBookingDetails.add(new BookingDetail(booking.getId(), booking.getCreatedDate(), booking.getPickupAddress(), booking.getDestinationAddress(), booking.getMoney(), booking.getState()));
                }
            }
            liveDataListBooking.setValue(listBookingDetails);
        });

        liveDataListBooking.setValue(listBookingDetails);
        mFlexibleAdapterBookingDetail = new FlexibleAdapter<>(listBookingDetails, this);

        liveDataListBooking.observe(getViewLifecycleOwner(), bookingDetails -> {
            mFlexibleAdapterBookingDetail.updateDataSet(bookingDetails);
        });

        binding.rcvBookingDetail.setAdapter(mFlexibleAdapterBookingDetail);
        binding.rcvBookingDetail.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }
    @Override
    protected void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject(this);
    }



}
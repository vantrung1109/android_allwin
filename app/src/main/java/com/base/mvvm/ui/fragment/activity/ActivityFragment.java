package com.base.mvvm.ui.fragment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.base.mvvm.BR;
import com.base.mvvm.R;

import com.base.mvvm.data.model.api.response.booking.MyBookingResponse;
import com.base.mvvm.databinding.FragmentActivityBinding;
import com.base.mvvm.di.component.FragmentComponent;
import com.base.mvvm.ui.base.BaseFragment;
import com.base.mvvm.ui.my_booking_detail.MyBookingDetailActivity;

import java.util.ArrayList;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.IFlexible;


public class ActivityFragment extends BaseFragment<FragmentActivityBinding, ActivityFragmentViewModel>
    implements FlexibleAdapter.OnItemClickListener
{
    FlexibleAdapter mFlexibleAdapterMyBooking;
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

        viewModel.showLoading();

        List<MyBookingResponse> myBookings = new ArrayList<>();

        viewModel.listMyBookings.observe(getViewLifecycleOwner(), myBookingResponses -> {
            myBookings.addAll(myBookingResponses);
        });

        mFlexibleAdapterMyBooking = new FlexibleAdapter<>(myBookings, this);

        viewModel.listMyBookings.observe(getViewLifecycleOwner(), myBookingResponses -> {
            mFlexibleAdapterMyBooking.updateDataSet(myBookings);
        });

        binding.rcvBookingDetail.setAdapter(mFlexibleAdapterMyBooking);
        binding.rcvBookingDetail.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }
    @Override
    protected void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject(this);
    }


    @Override
    public boolean onItemClick(View view, int i) {
        IFlexible flexibleItem = mFlexibleAdapterMyBooking.getItem(i);

        if (flexibleItem instanceof MyBookingResponse){
            MyBookingResponse myBookingResponse = (MyBookingResponse) flexibleItem;
            Intent intent = new Intent(getActivity(), MyBookingDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("myBookingResponse", myBookingResponse);
            Log.e("ActivityFragment", "onItemClick: " + myBookingResponse.getDriverVehicle().getName());
            intent.putExtras(bundle);
            startActivity(intent);
        }
        return false;
    }
}
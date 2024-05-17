package com.base.mvvm.ui.fragment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.base.mvvm.BR;
import com.base.mvvm.R;

import com.base.mvvm.data.model.api.response.booking.MyBookingResponse;
import com.base.mvvm.databinding.FragmentActivityBinding;
import com.base.mvvm.di.component.FragmentComponent;
import com.base.mvvm.ui.base.BaseFragment;
import com.base.mvvm.ui.fragment.HomeCallBack;
import com.base.mvvm.ui.my_booking_detail.MyBookingDetailActivity;

import java.util.ArrayList;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.IFlexible;


public class ActivityFragment extends BaseFragment<FragmentActivityBinding, ActivityFragmentViewModel>
    implements FlexibleAdapter.OnItemClickListener, HomeCallBack
{
    FlexibleAdapter mFlexibleAdapterMyBooking;
    List<MyBookingResponse> listMyBookingResponses;
    Integer currentPage = 0;
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
        showProgressbar("Activities Loading...");
        listMyBookingResponses = new ArrayList<>();
        mFlexibleAdapterMyBooking = new FlexibleAdapter<>(listMyBookingResponses, this);
        callApiAt(currentPage);
        viewModel.setListenerCallBack(this);
        binding.rcvBookingDetail.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int totalItemCount = layoutManager.getItemCount();
                int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();

                // Check if scrolled to the last item
                if (lastVisiblePosition == totalItemCount - 1) {
                    // Perform your action here (e.g., load more data, trigger a notification)
                    loadMoreData();
                }
            }
        });
        binding.rcvBookingDetail.setAdapter(mFlexibleAdapterMyBooking);
        binding.rcvBookingDetail.setLayoutManager(
                new LinearLayoutManager(getContext(),
                        LinearLayoutManager.VERTICAL,
                        false));

    }

    private void callApiAt(int page) {
        viewModel.callApiGetMyBooking(10, page);
    }

    @Override
    protected void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject(this);
    }

    private void loadMoreData() {
//        showProgressbar("Loading more data...");
        callApiAt(++currentPage);
        //viewModel.callApiGetMyBooking2(10, ++currentPage);
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

    @Override
    public void doSuccessGetData(Object data) {
        List<MyBookingResponse> myBookingResponses = (List<MyBookingResponse>) data;
        listMyBookingResponses.addAll(myBookingResponses);
        mFlexibleAdapterMyBooking.updateDataSet(listMyBookingResponses);
    }

    @Override
    public void doSuccessGetData(List<Object> data) {

    }

    @Override
    public void doSuccess() {

    }

    @Override
    public void doError() {

    }


//    @Override
//    public void showProgressbar(String msg) {
//        super.showProgressbar(msg);
//        Dialog dialog = new Dialog(getActivity());
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.layout_progressbar);
//        dialog.setCancelable(false);
//        dialog.show();
//    }
}
package com.base.mvvm.ui.fragment.home.discount;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.base.mvvm.R;
import com.base.mvvm.data.model.api.response.discount.DiscountResponse;
import com.base.mvvm.databinding.ActivityDiscountBinding;
import com.base.mvvm.di.component.ActivityComponent;
import com.base.mvvm.ui.base.BaseActivity;
import com.base.mvvm.ui.fragment.HomeCallBack;

import java.util.ArrayList;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.databinding.BR;

public class DiscountActivity extends BaseActivity<ActivityDiscountBinding, DiscountViewModel>
implements HomeCallBack {


    FlexibleAdapter mFlexibleAdapter;
    Integer currentPage = 0;
    List<DiscountResponse> listDiscountResponses;
    @Override
    public int getLayoutId() {
        return R.layout.activity_discount;
    }

    @Override
    public int getBindingVariable() {
        return BR.vm;
    }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listDiscountResponses = new ArrayList<>();
        viewModel.callDiscount(currentPage, 10);
        mFlexibleAdapter = new FlexibleAdapter<>(new ArrayList<>(), this );
        viewModel.setCallBack(this);
        viewBinding.rcvDiscount.setLayoutManager(new LinearLayoutManager(this));
        viewBinding.rcvDiscount.setAdapter(mFlexibleAdapter);
        viewBinding.rcvDiscount.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int totalItemCount = layoutManager.getItemCount();
                int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();

                // Check if scrolled to the last item
                if (totalItemCount == lastVisiblePosition + 1) {
                    loadMoreData();

                }
            }
        });
    }
    private void loadMoreData() {
//        showProgressbar("Loading more data...");
        viewModel.callDiscount(++currentPage, 10);
        //viewModel.callApiGetMyBooking2(10, ++currentPage);
    }

    @Override
    public void doSuccessGetData(Object data) {
        listDiscountResponses.addAll((List<DiscountResponse>) data);
        mFlexibleAdapter.updateDataSet(listDiscountResponses);
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
}

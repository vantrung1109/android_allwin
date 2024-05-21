package com.base.mvvm.ui.fragment.home.discount;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.base.mvvm.R;
import com.base.mvvm.data.model.api.response.discount.DiscountResponse;
import com.base.mvvm.data.model.api.response.service.ServiceResponse;
import com.base.mvvm.databinding.ActivityDiscountBinding;
import com.base.mvvm.di.component.ActivityComponent;
import com.base.mvvm.ui.base.BaseActivity;
import com.base.mvvm.ui.fragment.HomeCallBack;
import com.base.mvvm.ui.fragment.home.maps.MapActivity;

import java.util.ArrayList;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.databinding.BR;
import eu.davidea.flexibleadapter.items.IFlexible;

public class DiscountActivity extends BaseActivity<ActivityDiscountBinding, DiscountViewModel>
implements HomeCallBack,
    FlexibleAdapter.OnItemClickListener {

    private static final int REQUEST_CODE_DISCOUNT = 1;

    FlexibleAdapter mFlexibleAdapter;
    Integer currentPage = 0;
    List<DiscountResponse> listDiscountResponses;
    Double price;
    private DiscountResponse currentDiscountResponse;



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

        Bundle bundle = getIntent().getExtras();
        String priceString = "";
        if (bundle != null) {
            priceString = bundle.getString("price");
        }
        price= Double.parseDouble(priceString);

        listDiscountResponses = new ArrayList<>();
        viewModel.callDiscount(currentPage, 10);
        mFlexibleAdapter = new FlexibleAdapter<>(new ArrayList<>(), this );
        viewModel.setCallBack(this);
        viewBinding.rcvDiscount.setLayoutManager(new LinearLayoutManager(this));
        viewBinding.rcvDiscount.setAdapter(mFlexibleAdapter);

//        viewBinding.rcvDiscount.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
//                int totalItemCount = layoutManager.getItemCount();
//                int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();
//
//                // Check if scrolled to the last item
//                if (totalItemCount == lastVisiblePosition + 1) {
//                    loadMoreData();
//
//                }
//            }
//        });

        viewBinding.btnContinue.setOnClickListener(v -> {
                Intent intent = new Intent();
                IFlexible flexibleItem = mFlexibleAdapter.getItem(DiscountResponse.getCurrentPositionItem());
                currentDiscountResponse = (DiscountResponse) flexibleItem;
                Bundle bundle1 = new Bundle();
                bundle1.putDouble("discount_value", currentDiscountResponse.getDiscountValue());
                intent.putExtras(bundle1);
                setResult(Activity.RESULT_OK, intent);
                finishActivity(REQUEST_CODE_DISCOUNT);
                finish();
        });

    }
    private void loadMoreData() {
//        showProgressbar("Loading more data...");
        viewModel.callDiscount(++currentPage, 10 );
        //viewModel.callApiGetMyBooking2(10, ++currentPage);
    }

    @Override
    public void doSuccessGetData(Object data) {
        if (data instanceof List) {
            List<DiscountResponse> listDiscountResponses1 = ((List<DiscountResponse>)data);
            listDiscountResponses.addAll(listDiscountResponses1);

            for (int i = 0; i < listDiscountResponses.size(); i++) {
                if (listDiscountResponses.get(i).getLimitValueMin()!=null &&
                        price > listDiscountResponses.get(i).getLimitValueMin()) {
                    listDiscountResponses.get(i).setStatus(1);

                } else {
                    listDiscountResponses.get(i).setStatus(0);
                    Log.e("DiscountActivity", "doSuccessGetData: " + listDiscountResponses.get(i).getStatus());
                }
            }
            mFlexibleAdapter.updateDataSet(listDiscountResponses);
        }



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

    @Override
    public boolean onItemClick(View view, int position) {
        IFlexible flexibleItem = mFlexibleAdapter.getItem(position);
        Log.e("DiscountActivity", "onItemClick: " + position);
        if (flexibleItem instanceof DiscountResponse) {
            currentDiscountResponse = (DiscountResponse) flexibleItem;
        }
        return false;
    }
}

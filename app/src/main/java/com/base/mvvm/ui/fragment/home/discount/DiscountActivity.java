package com.base.mvvm.ui.fragment.home.discount;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
implements HomeCallBack {

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
            priceString = bundle.getString("price_service");
            if (bundle.getSerializable("discount") != null) {
                currentDiscountResponse = (DiscountResponse) bundle.getSerializable("discount");
            }
        }



        price = Double.parseDouble(priceString);
        listDiscountResponses = new ArrayList<>();
        // Call API get list discount
        viewModel.callDiscount(currentPage, 10);
        viewModel.setCallBack(this);

        // Set up adapter, recycler view
        mFlexibleAdapter = new FlexibleAdapter<>(new ArrayList<>(), this );
        viewBinding.rcvDiscount.setLayoutManager(new LinearLayoutManager(this));
        viewBinding.rcvDiscount.setAdapter(mFlexibleAdapter);

        // Handle Button Continue - Back to the MapActivity
        viewBinding.btnContinue.setOnClickListener(v -> {
            // Check if the current position is not null
            // get the current Position - the item selected
            if (DiscountResponse.getCurrentPosition() != null)
                currentDiscountResponse = (DiscountResponse) mFlexibleAdapter.getItem(
                        DiscountResponse.getCurrentPosition());
            if (currentDiscountResponse != null) {
                Intent resultIntent = new Intent();
                Bundle bundle1 = new Bundle();
                bundle1.putSerializable("discount", currentDiscountResponse);
                resultIntent.putExtras(bundle1);
                setResult(RESULT_OK, resultIntent);
                finish();
            } else
                finish();
        });

    }
    private void loadMoreData() {
        viewModel.callDiscount(++currentPage, 10 );
    }

    @Override
    public void doSuccessGetData(Object data) {
        if (data instanceof List) {
            List<DiscountResponse> listDiscountResponses1 = ((List<DiscountResponse>)data);
            listDiscountResponses.addAll(listDiscountResponses1);

            for (int i = 0; i < listDiscountResponses.size(); i++) {
                // Check if the price is greater than the minimum limit value && if the minimum limit value is exist
                if (listDiscountResponses.get(i).getLimitValueMin() != null
                        && price > listDiscountResponses.get(i).getLimitValueMin()) {
                    listDiscountResponses.get(i).setStatus(1);
                } else {
                    listDiscountResponses.get(i).setStatus(0);
                }

                // Check if the current discount response is not null, choose again the item selected
                if (currentDiscountResponse != null) {
                    if (currentDiscountResponse.getId().equals(listDiscountResponses.get(i).getId())) {
                        listDiscountResponses.get(i).setIsSelected(true);
                    } else {
                        listDiscountResponses.get(i).setIsSelected(false);
                    }
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


}

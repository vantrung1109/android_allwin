package com.base.mvvm.ui.fragment.home.cancel_trip;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.base.mvvm.R;
import com.base.mvvm.data.service.DatabaseService;
import com.base.mvvm.databinding.ActivityCancelTripBinding;
import com.base.mvvm.databinding.ActivityNoteBinding;
import com.base.mvvm.di.component.ActivityComponent;
import com.base.mvvm.ui.base.BaseActivity;

import java.util.ArrayList;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.SelectableAdapter;
import eu.davidea.flexibleadapter.databinding.BR;
import eu.davidea.flexibleadapter.items.IFlexible;

public class CancelTripActivity extends BaseActivity<ActivityCancelTripBinding, CancelTripViewModel>
    implements FlexibleAdapter.OnItemClickListener

{
    OnActivityInteractionListener mListener;
    FlexibleAdapter mFlexibleAdapter;
    FlexibleAdapter mFlexibleAdapterCancelRating;
    View currentView1, currentView2;
    int mode = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding.btnCancel.setEnabled(false);
        viewBinding.btnCancel.setBackground(getResources().getDrawable(R.drawable.btn_custom_disable,null));

        viewBinding.layoutReasonCancel.setOnClickListener(v -> {
            viewBinding.imgChooseReasons.setImageResource(R.drawable.bottom_head_arrow);
            viewBinding.rcvReasonCancel.setVisibility(View.VISIBLE);
            viewBinding.layoutReasonCancel.setBackground(getResources().getDrawable(R.drawable.background_account_profile,null));
            viewBinding.layoutRatingMain.setVisibility(View.GONE);
            mode = 1;
        });

        viewBinding.imgChooseReasons.setOnClickListener(v -> {
            viewBinding.imgChooseReasons.setImageResource(R.drawable.bottom_head_arrow);
            viewBinding.rcvReasonCancel.setVisibility(View.VISIBLE);
            viewBinding.layoutReasonCancel.setBackground(getResources().getDrawable(R.drawable.background_account_profile,null));
            viewBinding.layoutRatingMain.setVisibility(View.GONE);
            mode = 1;
        });

        mFlexibleAdapter = new FlexibleAdapter(DatabaseService.getInstance().getCancelItems(), this);
        viewBinding.rcvReasonCancel.setAdapter(mFlexibleAdapter);
        viewBinding.rcvReasonCancel.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        );
//
        mFlexibleAdapterCancelRating = new FlexibleAdapter(DatabaseService.getInstance().getCancelRatingItems(), this);
        viewBinding.rcvRating.setAdapter(mFlexibleAdapterCancelRating);
        viewBinding.rcvRating.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        );

        viewBinding.layoutChooseRating.setOnClickListener(v -> {
            viewBinding.imgChooseRating.setImageResource(R.drawable.bottom_head_arrow);
            viewBinding.rcvRating.setVisibility(View.VISIBLE);
            viewBinding.layoutChooseRating.setBackground(getResources().getDrawable(R.drawable.background_account_profile,null));
            mode = 2;
        });

        viewBinding.imgChooseRating.setOnClickListener(v -> {
            viewBinding.imgChooseRating.setImageResource(R.drawable.bottom_head_arrow);
            viewBinding.rcvRating.setVisibility(View.VISIBLE);
            viewBinding.layoutChooseRating.setBackground(getResources().getDrawable(R.drawable.background_account_profile,null));
            mode = 2;
        });

    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_cancel_trip;
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
    public boolean onItemClick(View view, int position) {
        IFlexible item;
        switch (mode){
            case 1:
                item = mFlexibleAdapter.getItem(position);
                break;
            case 2:
                item = mFlexibleAdapterCancelRating.getItem(position);
                break;
            default:
                return false;
        }
        Log.e("CancelTripActivity", "onItemClick: " + (boolean)(item instanceof CancelTripItem));
        if (item instanceof CancelTripItem) {
            CancelTripItem cancelTripItem = (CancelTripItem) item;
            viewBinding.tvReasonCancel.setText(cancelTripItem.getReason());
            viewBinding.imgChooseReasons.setImageResource(R.drawable.right_head_arrow);
            viewBinding.rcvReasonCancel.setVisibility(View.GONE);
            viewBinding.layoutReasonCancel.setBackground(getResources().getDrawable(R.drawable.edit_txt_custom, null));
            viewBinding.btnCancel.setEnabled(true);
            viewBinding.btnCancel.setBackground(getResources().getDrawable(R.drawable.btn_custom_enable,null));
            if (currentView1 != null) {
                currentView1.findViewById(R.id.img_radiobutton).setBackground(getResources().getDrawable(R.drawable.radiobutton_normal, null));
            }
            view.findViewById(R.id.img_radiobutton).setBackground(getResources().getDrawable(R.drawable.radiobutton_checked, null));
            currentView1 = view;
            viewBinding.layoutRatingMain.setVisibility(View.VISIBLE);
            return true;
        }
        if (item instanceof CancelRatingItem) {
            CancelRatingItem cancelRatingItem = (CancelRatingItem) item;
            viewBinding.tvRating.setText(cancelRatingItem.getStatus());
            viewBinding.imgChooseRating.setImageResource(R.drawable.right_head_arrow);
            viewBinding.rcvRating.setVisibility(View.GONE);
            viewBinding.layoutChooseRating.setBackground(getResources().getDrawable(R.drawable.edit_txt_custom, null));
            viewBinding.btnCancel.setEnabled(true);
            if (currentView2 != null) {
                currentView2.findViewById(R.id.img_radiobutton).setBackground(getResources().getDrawable(R.drawable.radiobutton_normal, null));
            }
            view.findViewById(R.id.img_radiobutton).setBackground(getResources().getDrawable(R.drawable.radiobutton_checked, null));
            currentView2 = view;
            return true;
        }
        return false;
    }
}

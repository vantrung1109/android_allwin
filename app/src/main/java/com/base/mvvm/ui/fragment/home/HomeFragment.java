package com.base.mvvm.ui.fragment.home;



import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.base.mvvm.BR;
import com.base.mvvm.R;

import com.base.mvvm.data.service.DatabaseService;
import com.base.mvvm.databinding.FragmentHomeBinding;
import com.base.mvvm.di.component.FragmentComponent;
import com.base.mvvm.ui.base.BaseFragment;

import eu.davidea.flexibleadapter.FlexibleAdapter;

public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeFragmentViewModel>{

    FlexibleAdapter mFlexibleAdapterTitleAddressSave, mFlexibleAdapterAddressSaveItem;

    @Override
    public int getBindingVariable() {
        return BR.vm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void performDataBinding() {

        mFlexibleAdapterTitleAddressSave = new FlexibleAdapter(DatabaseService.getInstance().getTitleAddressSave(), this.getActivity());
        binding.rcvItemTitleSaveAddress.setAdapter(mFlexibleAdapterTitleAddressSave);
        binding.rcvItemTitleSaveAddress.setLayoutManager(
                new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false)
        );

        mFlexibleAdapterAddressSaveItem = new FlexibleAdapter(DatabaseService.getInstance().getAddressSaveItems(), this.getActivity());
        binding.rcvItemAddressSave.setAdapter(mFlexibleAdapterAddressSaveItem);
        binding.rcvItemAddressSave.setLayoutManager(
                new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false)
        );

        binding.editPickupAddress.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                binding.deleteEditPickupAddress.setVisibility(View.VISIBLE);
            } else {
                binding.deleteEditPickupAddress.setVisibility(View.GONE);
            }
        });

        binding.editDestinationAddress.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                binding.deleteEditDestinationAddress.setVisibility(View.VISIBLE);
            } else {
                binding.deleteEditDestinationAddress.setVisibility(View.GONE);

            }
        });

        binding.deleteEditPickupAddress.setOnClickListener(v -> {
            binding.editPickupAddress.setText("");
        });

        binding.deleteEditDestinationAddress.setOnClickListener(v -> {
            binding.editDestinationAddress.setText("");
        });

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (binding.editPickupAddress.hasFocus() || binding.editDestinationAddress.hasFocus()) {
                    binding.layoutSaveAddress.setVisibility(View.GONE);
                    binding.rcvItemAddressSave.setVisibility(View.VISIBLE);
                } else {
                    binding.layoutSaveAddress.setVisibility(View.VISIBLE);
                    binding.rcvItemAddressSave.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };

        binding.editPickupAddress.addTextChangedListener(textWatcher);
        binding.editDestinationAddress.addTextChangedListener(textWatcher);

        binding.layoutMain.setOnClickListener(v -> {
            //Toast.makeText(this.getActivity(), "Click", Toast.LENGTH_SHORT).show();
            hideKeyboard();
            binding.layoutSaveAddress.setVisibility(View.VISIBLE);
            binding.rcvItemAddressSave.setVisibility(View.GONE);
        });

    }

    @Override
    protected void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject(this);
    }


}
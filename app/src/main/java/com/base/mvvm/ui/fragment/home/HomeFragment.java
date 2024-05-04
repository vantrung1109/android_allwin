package com.base.mvvm.ui.fragment.home;



import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.base.mvvm.BR;
import com.base.mvvm.R;

import com.base.mvvm.data.model.api.api_search.Prediction;
import com.base.mvvm.data.model.api.api_search.SearchPlaceApi;
import com.base.mvvm.data.service.DatabaseService;
import com.base.mvvm.databinding.FragmentHomeBinding;
import com.base.mvvm.di.component.FragmentComponent;
import com.base.mvvm.ui.base.BaseFragment;
import com.base.mvvm.ui.fragment.InterfaceCallBackApi;
import com.base.mvvm.ui.fragment.home.model.TitleAddressSave;

import java.util.ArrayList;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.IFlexible;

public class   HomeFragment extends BaseFragment<FragmentHomeBinding, HomeFragmentViewModel>
    implements FlexibleAdapter.OnItemClickListener, InterfaceCallBackApi<SearchPlaceApi>
{

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

        viewModel.setListenerCallBack(this);

        List<Prediction> listPredictions = new ArrayList<>();
        mFlexibleAdapterAddressSaveItem = new FlexibleAdapter(new ArrayList(), this);


        binding.rcvItemAddressSave.setAdapter(mFlexibleAdapterAddressSaveItem);
        binding.rcvItemAddressSave.setLayoutManager(
                new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false)
        );

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                check_rcv();
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
    public void doSuccessGetData(SearchPlaceApi searchPlaceApi) {
        mFlexibleAdapterAddressSaveItem.updateDataSet(searchPlaceApi.getPredictions());
    }

    @Override
    public void doSuccess() {

    }

    @Override
    public void doError() {

    }

    public void check_rcv() {
        if (binding.editPickupAddress.hasFocus() || binding.editDestinationAddress.hasFocus()) {
            binding.layoutSaveAddress.setVisibility(View.GONE);
            binding.rcvItemAddressSave.setVisibility(View.VISIBLE);
            binding.btnContinue.setVisibility(View.GONE);
            if (binding.editPickupAddress.hasFocus()){
                viewModel.getSearchPlaces(binding.editPickupAddress.getText().toString());
            }
            else{
                viewModel.getSearchPlaces(binding.editDestinationAddress.getText().toString());
            }
        } else {
            binding.layoutSaveAddress.setVisibility(View.VISIBLE);
            binding.rcvItemAddressSave.setVisibility(View.GONE);
            binding.btnContinue.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject(this);
    }

    @Override
    public boolean onItemClick(View view, int i) {
        IFlexible flexibleItemTittle = mFlexibleAdapterTitleAddressSave.getItem(i);
        if (flexibleItemTittle instanceof TitleAddressSave){
            Log.e("HomeFragment", "onItemClick: ");
        }

        IFlexible flexibleItem = mFlexibleAdapterAddressSaveItem.getItem(i);
        if (flexibleItem instanceof Prediction){
            Prediction prediction = (Prediction) flexibleItem;
            if (binding.editPickupAddress.hasFocus()){
                binding.editPickupAddress.setText(prediction.getDescription());

            }
            else{
                binding.editDestinationAddress.setText(prediction.getDescription());
            }
            binding.btnContinue.setVisibility(View.VISIBLE);
            binding.layoutSaveAddress.setVisibility(View.VISIBLE);
            binding.rcvItemAddressSave.setVisibility(View.GONE);
            hideKeyboard();
        }
        return false;
    }


}
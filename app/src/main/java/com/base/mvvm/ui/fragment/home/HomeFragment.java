package com.base.mvvm.ui.fragment.home;



import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.base.mvvm.BR;
import com.base.mvvm.R;

import com.base.mvvm.data.model.api.response.map.address_by_placeid.Location;
import com.base.mvvm.data.model.api.map_search.Prediction;
import com.base.mvvm.data.model.api.map_search.SearchPlaceApi;
import com.base.mvvm.data.service.DatabaseService;
import com.base.mvvm.databinding.FragmentHomeBinding;
import com.base.mvvm.di.component.FragmentComponent;
import com.base.mvvm.ui.base.BaseFragment;
import com.base.mvvm.ui.fragment.HomeCallBack;
import com.base.mvvm.ui.fragment.home.maps.MapActivity;
import com.base.mvvm.ui.fragment.home.model.TitleAddressSave;

import java.util.ArrayList;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.IFlexible;

public class   HomeFragment extends BaseFragment<FragmentHomeBinding, HomeFragmentViewModel>
    implements FlexibleAdapter.OnItemClickListener, HomeCallBack {

    FlexibleAdapter mFlexibleAdapterTitleAddressSave;
    FlexibleAdapter mFlexibleAdapterAddressPickup, mFlexibleAdapterAddressDestination;;
    Prediction prediction_pickup, prediction_destination;

    Location location_1;
    Location location_2;

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
                binding.rcvItemAddressDestination.setVisibility(View.GONE);
                binding.btnContinue.setVisibility(View.GONE);
            } else {
                binding.deleteEditPickupAddress.setVisibility(View.GONE);
            }
        });

        binding.editDestinationAddress.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                binding.deleteEditDestinationAddress.setVisibility(View.VISIBLE);
                binding.rcvItemAddressPickup.setVisibility(View.GONE);
                binding.btnContinue.setVisibility(View.GONE);
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

        // set callback api of viewmodel to this
        viewModel.setCallBack(this);

        List<Prediction> listPredictions = new ArrayList<>();

        // Using 2 adapter for 2 recyclerview express list address pickup and list address destination
        mFlexibleAdapterAddressPickup = new FlexibleAdapter(listPredictions, this);
        mFlexibleAdapterAddressDestination = new FlexibleAdapter(listPredictions, this);
        binding.rcvItemAddressPickup.setAdapter(mFlexibleAdapterAddressPickup);
        binding.rcvItemAddressPickup.setLayoutManager(
                new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false)
        );
        binding.rcvItemAddressDestination.setAdapter(mFlexibleAdapterAddressDestination);
        binding.rcvItemAddressDestination.setLayoutManager(
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
            //binding.layoutSaveAddress.setVisibility(View.VISIBLE);
        });

        binding.btnContinue.setOnClickListener(v -> {
            doContinue();
        });

    }

    public void  doContinue(){
        viewModel.setCallBack(this);
        if (binding.editPickupAddress.getText().toString().isEmpty() || binding.editDestinationAddress.getText().toString().isEmpty()){
            Toast.makeText(this.getActivity(), "Bạn phải chọn cả 2 địa điểm trước khi tiếp tục", Toast.LENGTH_SHORT).show();
            return;
        }

        if (prediction_pickup == null){
            IFlexible flexibleItem = mFlexibleAdapterAddressPickup.getItem(0);
            if (flexibleItem instanceof Prediction){
                Prediction prediction = (Prediction) flexibleItem;
                binding.editPickupAddress.setText(prediction.getDescription());
                prediction_pickup = prediction;
                binding.rcvItemAddressPickup.setVisibility(View.GONE);
            }
        }

        if (prediction_destination == null){
            IFlexible flexibleItem = mFlexibleAdapterAddressDestination.getItem(0);
            if (flexibleItem instanceof Prediction){
                Prediction prediction = (Prediction) flexibleItem;
                binding.editDestinationAddress.setText(prediction.getDescription());
                prediction_destination = prediction;
                binding.rcvItemAddressDestination.setVisibility(View.GONE);
            }
        }

        Intent intent = new Intent(this.getActivity(), MapActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("place_id_pickup", prediction_pickup.getPlace_id());
        bundle.putString("place_id_destination", prediction_destination.getPlace_id());
        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.getActivity().startActivityForResult(intent, 1);
    }


    public void check_rcv() {
        if (binding.editPickupAddress.hasFocus() || binding.editDestinationAddress.hasFocus()) {
            binding.layoutSaveAddress.setVisibility(View.GONE);
            binding.btnContinue.setVisibility(View.GONE);
            // show recyclerview
            if (binding.editPickupAddress.hasFocus())
                binding.rcvItemAddressPickup.setVisibility(View.VISIBLE);
            else if (binding.editDestinationAddress.hasFocus())
                binding.rcvItemAddressDestination.setVisibility(View.VISIBLE);

            if (binding.editPickupAddress.hasFocus()){
                viewModel.getSearchPlaces(binding.editPickupAddress.getText().toString());
            }
            else{
                viewModel.getSearchPlaces(binding.editDestinationAddress.getText().toString());
            }
        } else {
            binding.layoutSaveAddress.setVisibility(View.VISIBLE);
            binding.rcvItemAddressPickup.setVisibility(View.GONE);
            binding.rcvItemAddressDestination.setVisibility(View.GONE);
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

        IFlexible flexibleItemAddress = null;
        if (binding.editPickupAddress.hasFocus())
            flexibleItemAddress = mFlexibleAdapterAddressPickup.getItem(i);
        else
            flexibleItemAddress = mFlexibleAdapterAddressDestination.getItem(i);


        if (flexibleItemAddress instanceof Prediction){
            Prediction prediction = (Prediction) flexibleItemAddress;
            if (binding.editPickupAddress.hasFocus()){
                binding.editPickupAddress.setText(prediction.getDescription());
                prediction_pickup = prediction;
                binding.rcvItemAddressPickup.setVisibility(View.GONE);

            }
            else{
                binding.editDestinationAddress.setText(prediction.getDescription());
                prediction_destination = prediction;
                binding.rcvItemAddressDestination.setVisibility(View.GONE);
            }
            binding.layoutSaveAddress.setVisibility(View.VISIBLE);
            binding.btnContinue.setVisibility(View.VISIBLE);
            hideKeyboard();
        }
        return false;
    }


    @Override
    public void doSuccessGetData(Object data) {
        if (data instanceof SearchPlaceApi){
            SearchPlaceApi searchPlaceApi = (SearchPlaceApi) data;
            if (binding.editPickupAddress.hasFocus())
                mFlexibleAdapterAddressPickup.updateDataSet(searchPlaceApi.getPredictions());
            else if (binding.editDestinationAddress.hasFocus())
                mFlexibleAdapterAddressDestination.updateDataSet(searchPlaceApi.getPredictions());
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
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1){
            if (resultCode == RESULT_OK){
                prediction_destination = null;
                prediction_pickup = null;
            }
        }
    }
}
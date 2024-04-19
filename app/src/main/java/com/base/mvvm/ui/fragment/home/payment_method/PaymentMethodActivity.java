package com.base.mvvm.ui.fragment.home.payment_method;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.base.mvvm.R;
import com.base.mvvm.data.model.api.response.service.ServiceResponse;
import com.base.mvvm.databinding.ActivityMapBinding;
import com.base.mvvm.di.component.ActivityComponent;
import com.base.mvvm.ui.base.BaseActivity;
import com.base.mvvm.ui.fragment.home.maps.MapViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.databinding.BR;

public class PaymentMethodActivity extends BaseActivity<ActivityMapBinding, PaymentMethodViewModel> {

    FlexibleAdapter mFlexibleAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_map;
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


        List<ServiceResponse> serviceAutos = new ArrayList<>();

        viewModel.listServices.observe(this, serviceResponses -> {
            serviceAutos.addAll(serviceResponses);
        });

        mFlexibleAdapter = new FlexibleAdapter<>(serviceAutos, this);

        viewModel.listServices.observe(this, serviceResponses -> {
            mFlexibleAdapter.updateDataSet(serviceAutos);
        });


        mFlexibleAdapter = new FlexibleAdapter(serviceAutos, this);

        viewBinding.rcvVehicleOrder.setAdapter(mFlexibleAdapter);
        viewBinding.rcvVehicleOrder.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));




    }

}

package com.base.mvvm.ui.fragment.home.maps;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.base.mvvm.R;
import com.base.mvvm.data.model.api.response.service.ServiceResponse;
import com.base.mvvm.databinding.ActivityMapBinding;
import com.base.mvvm.di.component.ActivityComponent;
import com.base.mvvm.ui.base.BaseActivity;
import com.base.mvvm.ui.fragment.InterfaceCallBackApi;
import com.base.mvvm.ui.fragment.home.model.VehicleOrder;
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
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.databinding.BR;
import eu.davidea.flexibleadapter.items.IFlexible;

public class MapActivity extends BaseActivity<ActivityMapBinding, MapViewModel>
        implements OnMapReadyCallback, FlexibleAdapter.OnItemClickListener, InterfaceCallBackApi<List<ServiceResponse>>

{

    private final int FINE_PERMISSION_CODE = 1;
    private GoogleMap myMap;

    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    FlexibleAdapter mFlexibleAdapter;

    private BottomSheetBehavior bottomSheetBehavior;
    private BottomSheetBehavior bottomSheetBehaviorPayment;

    View currentview;

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

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getLastLocation();

        mFlexibleAdapter = new FlexibleAdapter<>(new ArrayList<>(), this);

        RecyclerView rcvVehicleOrder = findViewById(R.id.rcv_vehicle_order);
        viewModel.getServices();
        viewModel.setListenerCallBack(this);

        rcvVehicleOrder.setAdapter(mFlexibleAdapter);
        rcvVehicleOrder.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


        bottomSheetBehavior = BottomSheetBehavior.from(viewBinding.layoutBottomSheet);
        bottomSheetBehaviorPayment = BottomSheetBehavior.from(viewBinding.layoutBottomSheetPayment);
        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull @org.jetbrains.annotations.NotNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehaviorPayment.setHideable(true);
                    bottomSheetBehaviorPayment.setState(BottomSheetBehavior.STATE_HIDDEN);
                } else {
                   bottomSheetBehaviorPayment.setHideable(false);
                   bottomSheetBehaviorPayment.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

            }

            @Override
            public void onSlide(@NonNull @org.jetbrains.annotations.NotNull View bottomSheet, float slideOffset) {

            }
        });


        //bottom sheet payment
        TextView tvCash = findViewById(R.id.tv_cash);
        TextView tvDiscount = findViewById(R.id.tv_discount);
        TextView tvNote = findViewById(R.id.tv_note);
        tvCash.setOnClickListener(v -> {
            viewModel.navigateToPaymentMethod();
        });
        tvDiscount.setOnClickListener(v -> {
            viewModel.navigateToDiscount();
        });
        tvNote.setOnClickListener(v -> {
            viewModel.navigateToNote();
        });
    }

    @Override
    public void doSuccessGetData(List<ServiceResponse> serviceResponses) {
        mFlexibleAdapter.updateDataSet(serviceResponses);
    }

    @Override
    public void doSuccess() {

    }

    @Override
    public void doError() {

    }


    private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, FINE_PERMISSION_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    currentLocation = location;
                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                    mapFragment.getMapAsync(MapActivity.this);
                }
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        myMap = googleMap;
        LatLng sydney = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        myMap.addMarker(new MarkerOptions().position(sydney).title("My current Location"));
        myMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        myMap.animateCamera(CameraUpdateFactory.zoomTo(16.0f));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == FINE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            } else {
                Toast.makeText(this, "Location permission is denied, please allow the permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onItemClick(View view, int i) {
        IFlexible item = mFlexibleAdapter.getItem(i);
        if (item instanceof ServiceResponse) {
            ServiceResponse serviceResponse = (ServiceResponse) item;
            if (currentview != null)
                currentview.setBackground(MapActivity.this.getResources().getDrawable(R.drawable.background_vehicle_normal, null));
            view.setBackground(MapActivity.this.getResources().getDrawable(R.drawable.background_vehicle_focus, null));
            currentview = view;
        }
        return false;
    }



}

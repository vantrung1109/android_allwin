package com.base.mvvm.ui.fragment.home.maps;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.base.mvvm.BR;
import com.base.mvvm.R;
import com.base.mvvm.data.model.api.address_by_placeid.AddressByPlaceId;
import com.base.mvvm.data.model.api.distance.DistanceResponse;
import com.base.mvvm.data.model.api.response.service.ServiceResponse;
import com.base.mvvm.databinding.ActivityMapBinding;
import com.base.mvvm.di.component.ActivityComponent;
import com.base.mvvm.ui.base.BaseActivity;
import com.base.mvvm.ui.fragment.HomeCallBack;
import com.base.mvvm.ui.fragment.home.discount.DiscountActivity;
import com.base.mvvm.ui.fragment.home.note.NoteActivity;
import com.base.mvvm.utils.DisplayUtils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.IFlexible;
import okhttp3.internal.Util;

public class MapActivity extends BaseActivity<ActivityMapBinding, MapViewModel>
        implements OnMapReadyCallback, FlexibleAdapter.OnItemClickListener, HomeCallBack {

    private static final int REQUEST_CODE_DISCOUNT = 1;
    private static final int REQUEST_CODE_NOTE = 2;
    private final int FINE_PERMISSION_CODE = 1;
    private GoogleMap myMap;

    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    FlexibleAdapter mFlexibleAdapter;

    private BottomSheetBehavior bottomSheetBehavior, bottomSheetBehaviorPayment, bottomSheetBehaviorWaiting;


    View currentview;
    ServiceResponse currentServiceResponse;
    List<ServiceResponse> serviceResponses;

    TextView btnOrder;
    RecyclerView rcvServices;
    TextView tvDiscount, tvNote, tvCash;

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
        viewModel.setListenerCallBack(this);
        serviceResponses = new ArrayList<>();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getLastLocation();

        bottomSheetBehavior = BottomSheetBehavior.from(viewBinding.layoutBottomSheet);
        bottomSheetBehaviorPayment = BottomSheetBehavior.from(viewBinding.layoutBottomSheetPayment);
        bottomSheetBehaviorWaiting = BottomSheetBehavior.from(viewBinding.layoutBottomSheetWaiting);
        bottomSheetBehaviorWaiting.setHideable(true);
        bottomSheetBehaviorWaiting.setState(BottomSheetBehavior.STATE_HIDDEN);

        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull @org.jetbrains.annotations.NotNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehaviorPayment.setHideable(true);
                    bottomSheetBehaviorPayment.setState(BottomSheetBehavior.STATE_HIDDEN);
                } else if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    bottomSheetBehaviorPayment.setHideable(false);
                    bottomSheetBehaviorPayment.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

            }

            @Override
            public void onSlide(@NonNull @org.jetbrains.annotations.NotNull View bottomSheet, float slideOffset) {

            }
        });


        //bottom sheet payment
        tvCash = findViewById(R.id.tv_cash);
        tvDiscount = findViewById(R.id.tv_discount);
        tvNote = findViewById(R.id.tv_note);
        btnOrder = findViewById(R.id.btn_order);
        tvCash.setOnClickListener(v -> {
            viewModel.navigateToPaymentMethod();
        });
        tvDiscount.setOnClickListener(v -> {
            navigateToDiscount();
        });
        tvNote.setOnClickListener(v -> {
            navigateToNote();
        });
//        btnOrder.setEnabled(false);
//        btnOrder.setBackground(getResources().getDrawable(R.drawable.btn_disable, null));


        // Get pick up, destination
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String place_id_pickup = "";
        String place_id_destination = "";
        if (bundle != null) {
            place_id_pickup = bundle.getString("place_id_pickup");
            place_id_destination = bundle.getString("place_id_destination");
        }

        // Call Api Get Distance
        viewModel.getAddressByPlaceId(place_id_pickup, place_id_destination);

        // Call Api Services
        mFlexibleAdapter = new FlexibleAdapter<>(new ArrayList<>(), this);
        rcvServices = findViewById(R.id.rcv_services);
        rcvServices.setAdapter(mFlexibleAdapter);
        rcvServices.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        // Create Booking
        btnOrder.setOnClickListener(v -> {
            if (currentServiceResponse != null) {
                viewModel.bookingCreateRequest.observe(this, bookingCreateRequest -> {
                    if (bookingCreateRequest != null) {
                        bookingCreateRequest.setServiceId(currentServiceResponse.getId());
                        bookingCreateRequest.setMoney(Double.parseDouble(currentServiceResponse.getPrice()));
                        bookingCreateRequest.setPromotionMoney(0.0);
                    }
                });
            }
            bottomSheetBehavior.setHideable(true);
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            bottomSheetBehaviorPayment.setHideable(true);
            bottomSheetBehaviorPayment.setState(BottomSheetBehavior.STATE_HIDDEN);
            bottomSheetBehaviorWaiting.setHideable(false);
            bottomSheetBehaviorWaiting.setState(BottomSheetBehavior.STATE_COLLAPSED);
            viewModel.createBookingRequest();
        });

        viewBinding.buttonBack.setOnClickListener(v -> {
            finish();
        });

    }

    private void getLastLocation() {
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
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(16.0f));

        viewModel.locationOrigin.observe(this, location -> {
            viewModel.locationDestination.observe(this, location2 -> {
                if (location2 != null && location != null) {
                    LatLng origin = new LatLng(location.getLat(), location.getLng());
                    LatLng destination = new LatLng(location2.getLat(), location2.getLng());
                    MarkerOptions markerOrigin = new MarkerOptions().position(origin).title("Origin Location");
                    MarkerOptions markerDestination = new MarkerOptions().position(destination).title("Destination Location");

                    // Draw a line between the two markers
                    PolylineOptions polylineOptions = new PolylineOptions()
                            .add(markerOrigin.getPosition())
                            .add(markerDestination.getPosition())
                            .color(Color.RED)
                            .width(5f);
                    googleMap.addMarker(markerOrigin);
                    googleMap.addMarker(markerDestination);
                    googleMap.addPolyline(polylineOptions);

                    // Adjust the camera to show both markers and the line
                    LatLngBounds.Builder builder = new LatLngBounds.Builder();
                    builder.include(markerOrigin.getPosition());
                    builder.include(markerDestination.getPosition());
                    LatLngBounds bounds = builder.build();
                    //googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));
                }
            });
        });

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
            currentServiceResponse = (ServiceResponse) item;
            if (currentview != null) {
                currentview.setBackground(MapActivity.this.getResources().getDrawable(R.drawable.background_vehicle_normal, null));
            }
            if (i != 0) {
                rcvServices.getChildAt(0).setBackground(MapActivity.this.getResources().getDrawable(R.drawable.background_vehicle_normal, null));
            }
            view.setBackground(MapActivity.this.getResources().getDrawable(R.drawable.background_vehicle_focus, null));

            currentview = view;
        }
        return false;
    }


    private int count = 0;

    @SuppressLint("CheckResult")
    @Override
    public void doSuccessGetData(Object data) {

        if (data instanceof List) {
            serviceResponses = (List<ServiceResponse>) data;
            currentServiceResponse = serviceResponses.get(0);
            mFlexibleAdapter.updateDataSet(serviceResponses);
            Log.e("MapActivity", "current: " + currentview);
        }
        if (data instanceof AddressByPlaceId) {
            AddressByPlaceId addressByPlaceId = (AddressByPlaceId) data;

        }
        if (data instanceof DistanceResponse) {
            DistanceResponse distanceResponse = (DistanceResponse) data;
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

    public void navigateToDiscount() {
        Intent intent = new Intent(application, DiscountActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("price", currentServiceResponse.getPrice());
        intent.putExtras(bundle);
        startActivityForResult(intent, REQUEST_CODE_DISCOUNT);
    }

    public void navigateToNote() {
        Intent intent = new Intent(application, NoteActivity.class);
        startActivityForResult(intent, REQUEST_CODE_NOTE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_DISCOUNT && resultCode == RESULT_OK) {
            // Get the data from the Intent
            Log.e("MapActivity", "onActivityResult: " + data);
            if (data != null) {
                Bundle bundle = data.getExtras();
                if (bundle != null && bundle.containsKey("discount_value")) {
                    Double discount = bundle.getDouble("discount_value");
                    if (discount != null) {
                        Double price = Double.parseDouble(currentServiceResponse.getPrice());
                        Log.e("MapActivity", "price: " + price);
                        Double total = price - discount;
                        currentServiceResponse.setPrice(String.valueOf(total));
                        mFlexibleAdapter.notifyDataSetChanged();
                        tvDiscount.setText(DisplayUtils.custom_money_discount_map(discount));
                    }
                }
            } else {
                Log.e("MapActivity", "data is null");
            }
        } else if (requestCode == REQUEST_CODE_NOTE && resultCode == RESULT_OK) {
            // Get the data from the Intent
            Log.e("MapActivity", "onActivityResult: " + data);
            if (data != null) {
                String note = data.getStringExtra("note");
                if (note != null) {
                    tvNote.setText(note);
                } else if (note == "") {
                    tvNote.setText("Ghi chú");
                }
            } else {
                Log.e("MapActivity", "data from noteActivity is null");
            }
        }
    }

}

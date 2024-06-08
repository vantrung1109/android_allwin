package com.base.mvvm.ui.fragment.home.maps;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.base.mvvm.BR;
import com.base.mvvm.R;
import com.base.mvvm.data.model.api.response.map.address_by_placeid.AddressByPlaceId;
import com.base.mvvm.data.model.api.response.map.distance.DistanceResponse;
import com.base.mvvm.data.model.api.response.discount.DiscountResponse;
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.IFlexible;

public class MapActivity extends BaseActivity<ActivityMapBinding, MapViewModel>
        implements OnMapReadyCallback, FlexibleAdapter.OnItemClickListener, HomeCallBack {

    private static final int REQUEST_CODE_DISCOUNT = 1;
    private static final int REQUEST_CODE_NOTE = 2;
    private final int FINE_PERMISSION_CODE = 1;
    private GoogleMap myMap;
    private SupportMapFragment mapFragment;

    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    FlexibleAdapter mFlexibleAdapter;

    private BottomSheetBehavior bottomSheetBehavior, bottomSheetBehaviorPayment, bottomSheetBehaviorWaiting;
    View currentview;
    ServiceResponse currentServiceResponse;

    // Navigate Discount and return value
    DiscountResponse currentDiscount;

    // Navigate Note and return value
    String customerNote;
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
                        // set service id, money, customer note
                        bookingCreateRequest.setServiceId(currentServiceResponse.getId());
                        bookingCreateRequest.setMoney(Double.parseDouble(currentServiceResponse.getPrice()));
                        bookingCreateRequest.setCustomerNote(customerNote);
                        // set promotion money (if promotion price is not null)
                        if (currentDiscount != null ){
                            bookingCreateRequest.setPromotionMoney(currentDiscount.getDiscountValue());
                            // set promotion id false, need to fix
                            //bookingCreateRequest.setPromotionId(currentDiscount.getId());
                        }
                        else
                            // set money and promotion money (if promotion price is null
                            bookingCreateRequest.setPromotionMoney(0.0);
                    }
                });
            }
            // Hide the bottom sheet payment
            bottomSheetBehavior.setHideable(true);
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            bottomSheetBehaviorPayment.setHideable(true);
            bottomSheetBehaviorPayment.setState(BottomSheetBehavior.STATE_HIDDEN);
            // Show the bottom sheet waiting
            bottomSheetBehaviorWaiting.setHideable(false);
            bottomSheetBehaviorWaiting.setState(BottomSheetBehavior.STATE_COLLAPSED);

            // Raise the height of the map
            ViewGroup.LayoutParams params = mapFragment.getView().getLayoutParams();
            params.height = (int) application.getApplicationContext().getResources().getDimension(R.dimen._470sdp);
            mapFragment.getView().setLayoutParams(params);
            viewModel.createBookingRequest();
        });

        viewBinding.buttonBack.setOnClickListener(v -> {
            finish();
        });

//         set bottom sheet payment hideable or not
        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull @org.jetbrains.annotations.NotNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.setHalfExpandedRatio(0.7f);
//                    bottomSheetBehaviorPayment.setHideable(true);
//                    bottomSheetBehaviorPayment.setState(BottomSheetBehavior.STATE_HIDDEN);
                } else if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    bottomSheetBehavior.setPeekHeight(application.getApplicationContext().getResources().getDimensionPixelSize(R.dimen._280sdp));

//                    bottomSheetBehaviorPayment.setHideable(false);
//                    bottomSheetBehaviorPayment.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

            }

            @Override
            public void onSlide(@NonNull @org.jetbrains.annotations.NotNull View bottomSheet, float slideOffset) {

            }
        });

    }

    private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        !=
                        PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    currentLocation = location;
                    mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                    mapFragment.getMapAsync(MapActivity.this);
                }
            }
        });

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        myMap = googleMap;
        //LatLng current = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());


        viewModel.locationOrigin.observe(this, location -> {
            viewModel.locationDestination.observe(this, location2 -> {
                if (location2 != null && location != null) {
                    LatLng origin = new LatLng(location.getLat(), location.getLng());
                    LatLng destination = new LatLng(location2.getLat(), location2.getLng());

                    String originStr = origin.latitude + "," + origin.longitude;
                    String destinationStr = destination.latitude + "," + destination.longitude;
                    viewModel.getDirection(originStr, destinationStr);

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
        else if (data instanceof JsonObject) {
            JsonObject jsonObject = (JsonObject) data;
            JsonArray routes = jsonObject.getAsJsonArray("routes");
            if (routes.size() > 0) {
                JsonObject route = routes.get(0).getAsJsonObject();
                JsonObject polyline = route.getAsJsonObject("overview_polyline");
                String points = polyline.get("points").getAsString();
                List<LatLng> decodedPath = decodePoly(points);

                // Draw polyline on map
                myMap.addPolyline(new PolylineOptions().addAll(decodedPath).color(Color.BLUE).width(10));
                // Add marker to the start and end of the polyline
                LatLng originLatLng = decodedPath.get(0);
                LatLng destinationLatLng = decodedPath.get(decodedPath.size() - 1);
                myMap.addMarker(new MarkerOptions().position(originLatLng).title("Pickup Location"))
                        .setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                myMap.addMarker(new MarkerOptions().position(destinationLatLng).title("Destination Location"));

                // Move camera to the start of the polyline
                myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(originLatLng, 16.0f));
            }
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
        // if discount is not null, pass it to DiscountActivity
        if (currentDiscount != null) {
            bundle.putSerializable("discount", currentDiscount);
            bundle.putString("price_service", currentServiceResponse.getPrice());
        }
        // if discount is null, just pass the price of the service to DiscountActivity
        else
            bundle.putString("price_service", currentServiceResponse.getPrice());
        intent.putExtras(bundle);
        startActivityForResult(intent, REQUEST_CODE_DISCOUNT);
    }

    public void navigateToNote() {
        Intent intent = new Intent(application, NoteActivity.class);
        Bundle bundle = new Bundle();
        // if note is not null, pass it to NoteActivity
        if (customerNote != null) {
            bundle.putString("note", customerNote);
        }
        intent.putExtras(bundle);
        startActivityForResult(intent, REQUEST_CODE_NOTE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // From DiscountActivity
        if (requestCode == REQUEST_CODE_DISCOUNT && resultCode == RESULT_OK) {
            // Get the data from the DiscountActivity
            if (data != null) {
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    // Get the discount from the DiscountActivity
                    currentDiscount = (DiscountResponse) bundle.getSerializable("discount");
                    if (currentDiscount != null) {
                        // Set the promotion price
                        currentServiceResponse.setPromotionPrice(String.valueOf(currentDiscount.getDiscountValue()));
                        mFlexibleAdapter.notifyDataSetChanged();
                        // Set the discount value on the textview chosen discount
                        tvDiscount.setText(DisplayUtils.custom_money_discount_map(currentDiscount.getDiscountValue()));
                        tvDiscount.setTextColor(getResources().getColor(R.color.green_text, null));

                    }
                }
            } else {
                Log.e("MapActivity", "data is null");
            }
        }
        // From NoteActivity
        else if (requestCode == REQUEST_CODE_NOTE && resultCode == RESULT_OK) {
            // Get the data from the NoteActivity
            if (data != null) {
                String note = data.getStringExtra("note");
                if (note != null) {
                    tvNote.setText(note);
                    customerNote = note;
                    tvNote.setTextColor(getResources().getColor(R.color.red, null));
                } else if (note == "") {
                    tvNote.setText("Ghi chú");
                }
            } else {
                Log.e("MapActivity", "data from noteActivity is null");
            }
        }
    }

    // Giải mã polyline
    private List<LatLng> decodePoly(String encoded) {
        List<LatLng> poly = new ArrayList<>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((lat / 1E5), (lng / 1E5));
            poly.add(p);
        }

        return poly;
    }

}

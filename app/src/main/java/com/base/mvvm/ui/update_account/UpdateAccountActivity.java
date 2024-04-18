package com.base.mvvm.ui.update_account;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.base.mvvm.BuildConfig;
import com.base.mvvm.R;
import com.base.mvvm.constant.Constants;
import com.base.mvvm.data.model.api.ResponseWrapper;
import com.base.mvvm.data.model.api.request.UpdateProfileRequest;
import com.base.mvvm.data.model.api.response.customer.AccountResponse;
import com.base.mvvm.databinding.ActivityUpdateAccountBinding;
import com.base.mvvm.di.component.ActivityComponent;
import com.base.mvvm.ui.base.BaseActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicReference;

import eu.davidea.flexibleadapter.databinding.BR;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import timber.log.Timber;

public class UpdateAccountActivity extends BaseActivity<ActivityUpdateAccountBinding, UpdateAccountViewModel> {
    private Bitmap updatedAvatar;
    private static final int CAMERA_REQUEST = 100;
    private static final int STORAGE_REQUEST = 200;
    String cameraPermission[];
    String storagePermission[];
    @Override
    public int getLayoutId() {
        return R.layout.activity_update_account;
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

        viewModel.isShowPassWord.addOnPropertyChangedCallback(new androidx.databinding.Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(androidx.databinding.Observable sender, int propertyId) {
                if(!viewModel.isShowPassWord.get()){
                    viewBinding.editPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }else {
                    viewBinding.editPassword.setTransformationMethod(null);;
                }
            }
        });

        // allowing permissions of gallery and camera
        cameraPermission = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        AccountResponse profile = new AccountResponse();
        profile = viewModel.profile.get();

        if (profile != null) {
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher_round)
                    .error(R.mipmap.ic_launcher_round);
            Glide.with(this).load(BuildConfig.MEDIA_URL + "v1/file/download" + viewModel.avatar.get()).apply(options).into(viewBinding.imgAvatar);
        }


//        viewModel.isShowPassWord.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
//            @Override
//            public void onPropertyChanged(Observable sender, int propertyId) {
//                if(!viewModel.isShowPassWord.get()){
//                    viewBinding.editPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                }else {
//                    viewBinding.editPassword.setTransformationMethod(null);;
//                }
//            }
//        });
//        viewBinding.editPassword.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (s.length() < 1 && viewBinding.editName.getText().toString().isEmpty() && viewBinding.editEmail.getText().toString().isEmpty()){
//                    viewBinding.btnUpdate.setEnabled(false);
//                    viewBinding.btnUpdate.setBackground(getResources().getDrawable(R.drawable.btn_custom_disable, null));
//                } else {
//                    viewBinding.btnUpdate.setEnabled(true);
//                    viewBinding.btnUpdate.setBackground(getResources().getDrawable(R.drawable.btn_custom_enable, null));
//                }
//            }
//            @Override
//            public void afterTextChanged(android.text.Editable s) {
//            }
//        });

    }

//    public void uploadFile(){
//        viewModel.showLoading();
//        // Upload image if necessary
//        if (updatedAvatar != null) {
//            // Upload avatar then update profile
//            // Convert the Bitmap to a byte array
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            updatedAvatar.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
//            byte[] imageByteArray = byteArrayOutputStream.toByteArray();
//
//            // Create a request body for the image
//            RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), imageByteArray);
//
//            // Create a multipart request builder
//            MultipartBody.Builder requestBodyBuilder = new MultipartBody.Builder()
//                    .setType(MultipartBody.FORM);
//            requestBodyBuilder.addFormDataPart("file", "image.jpg", requestFile);
//            requestBodyBuilder.addFormDataPart("type", Constants.FILE_TYPE_AVATAR);
//            MultipartBody responseBody = requestBodyBuilder.build();
//            UpdateProfileRequest request = prepareRequest();
//
//            viewModel.compositeDisposable.add(viewModel.uploadAvatar(responseBody)
//                            .subscribeOn(Schedulers.io())
//                            .observeOn(AndroidSchedulers.mainThread())
//                            .subscribe(response -> {
//                                if (response.isResult()) {
//                                    if (response.getData().getFilePath() != null) {
//                                        request.setAvatar(response.getData().getFilePath());
//                                    }
//                                    viewModel.showSuccessMessage("Update File successfully");
//                                } else {
//                                    viewModel.showErrorMessage(response.getMessage());
//                                }
//                                viewModel.hideLoading();
//                                setResult(Activity.RESULT_OK);
//                                finish();
//                            }, err -> {
//                                Timber.tag("uploadFile: ").e(err);
//                                viewModel.hideLoading();
//                                viewModel.showErrorMessage(application.getString(R.string.newtwork_error));
//                            }));
//        }
//    }

//    public void updateProfile(){
////        if(viewModel.profile.get() == null || Objects.equals(viewModel.profile.get().getName(), "")){
////            viewModel.showErrorMessage("Pls fill name");
////        }
//
//        viewModel.showLoading();
//        // Upload image if necessary
//        if (updatedAvatar != null) {
//            // Upload avatar then update profile
//            // Convert the Bitmap to a byte array
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            updatedAvatar.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
//            byte[] imageByteArray = byteArrayOutputStream.toByteArray();
//
//            // Create a request body for the image
//            RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), imageByteArray);
//
//            // Create a multipart request builder
//            MultipartBody.Builder requestBodyBuilder = new MultipartBody.Builder()
//                    .setType(MultipartBody.FORM);
//            requestBodyBuilder.addFormDataPart("file", "image.jpg", requestFile);
//            requestBodyBuilder.addFormDataPart("type", Constants.FILE_TYPE_AVATAR);
//
//            UpdateProfileRequest request = prepareRequest();
//            AtomicReference<String> check = new AtomicReference<>("hehehe");
//            Observable<ResponseWrapper<String>>
//                    uploadAndProfileUpdateObservable = viewModel.uploadAvatar(requestBodyBuilder.build())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .flatMap(uploadResponse -> {
//                        if (uploadResponse.isResult()) {
//                            check.set("kaka");
//                        }
//                        else check.set(uploadResponse.getData().getFilePath());
//                        if (uploadResponse.isResult() && uploadResponse.getData().getFilePath() != null) {
//                            request.setAvatar(uploadResponse.getData().getFilePath());
//                        }
//                        return viewModel.updateProfile(request)
//                                .subscribeOn(Schedulers.io());
//                    });
//
//            viewModel.compositeDisposable.add(uploadAndProfileUpdateObservable
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(response -> {
//                        if (response.isResult()) {
//                            viewModel.showSuccessMessage(application.getString(R.string.update_profile_success));
//                        } else {
//
//                            viewModel.showErrorMessage(response.getMessage());
//                        }
//                        viewModel.hideLoading();
//
//                        setResult(Activity.RESULT_OK);
//                        finish();
//                    }, err -> {
//                        Log.e("updateProfile: ", check.get());
//                        viewModel.hideLoading();
//                        viewModel.showErrorMessage("updateProfile: " + check.get());
////                        viewModel.showErrorMessage(application.getString(R.string.newtwork_error));
//                    }));
//
//        } else {
//            // Update profile only
//            handleUpdateProfile();
//        }
//    }

//    public void handleUpdateProfile() {
//        UpdateProfileRequest request = prepareRequest();
//        Log.e("updateProfile: ", request.toString());
//        viewModel.compositeDisposable.add(viewModel.updateProfile(request)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(response -> {
//                    // render the avatar
//                    if (response.isResult()) {
//                        viewModel.showSuccessMessage(application.getString(R.string.update_profile_success));
//                    } else {
//                        viewModel.showErrorMessage(response.getMessage());
//                    }
//                    viewModel.hideLoading();
//                    setResult(Activity.RESULT_OK);
//                    finish();
//                }, err -> {
//                    viewModel.hideLoading();
//                    viewModel.showErrorMessage(application.getString(R.string.newtwork_error));
//                }));
//    }

//    private UpdateProfileRequest prepareRequest() {
//        UpdateProfileRequest request = new UpdateProfileRequest();
//        request.setName(viewModel.profile.get().getName());
//        request.setOldPassword(viewModel.profile.get().get);
//        request.setAvatar(viewModel.avatar.get());
//        return request;
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ImagePicker.REQUEST_CODE) {
            if (resultCode == RESULT_OK && data != null) {
                Uri resultUri = data.getData();
                if(resultUri == null) return;
                final InputStream imageStream;
                try {
                    imageStream = getContentResolver().openInputStream(resultUri);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                final Bitmap photo = BitmapFactory.decodeStream(imageStream);
                viewBinding.imgAvatar.setImageBitmap(photo);
                updatedAvatar = photo;
            }
        }

    }

    public void getNewAvatar() {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {
                    if (!checkCameraPermission()) {
                        requestCameraPermission();
                    } else {
                        takeFromCamera();
                    }
                } else if (options[item].equals("Choose from Gallery")) {
                    if (!checkStoragePermission()) {
                        requestStoragePermission();
                    } else {
                        takeFromGallery();
                    }
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    private void requestStoragePermission() {
        requestPermissions(storagePermission, STORAGE_REQUEST);
    }

    private void requestCameraPermission() {
        requestPermissions(cameraPermission, CAMERA_REQUEST);
    }
    private void takeFromCamera() {
        ImagePicker.with(UpdateAccountActivity.this)
                .cameraOnly()
                .cropSquare()
                .start();
    }

    private void takeFromGallery() {
        ImagePicker.with(UpdateAccountActivity.this)
                .galleryOnly()
                .cropSquare()
                .start();
    }
    private Boolean checkStoragePermission() {
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result;
    }

    // checking camera permissions
    private Boolean checkCameraPermission() {
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);
        boolean result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result && result1;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CAMERA_REQUEST: {
                if (grantResults.length > 0) {
                    boolean camera_accepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean writeStorageaccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if (camera_accepted && writeStorageaccepted) {
                        takeFromCamera();
                    } else {
                        viewModel.showErrorMessage("Please Enable Camera and Storage Permissions");
                    }
                }
            }
            break;
            case STORAGE_REQUEST: {
                if (grantResults.length > 0) {
                    boolean writeStorageaccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (writeStorageaccepted) {
                        takeFromGallery();
                    } else {
                        viewModel.showErrorMessage("Please Enable Storage Permissions");
                    }
                }
            }
            break;
        }
    }
}

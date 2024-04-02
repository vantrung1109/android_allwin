package com.base.mvvm.utils;

import android.text.TextUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.base.mvvm.BuildConfig;
import com.base.mvvm.R;
import com.bumptech.glide.Glide;

import java.util.List;


public final class BindingUtils {
    @BindingAdapter("android:src")
    public static void setImageUrl(ImageView view, String url) {
        if (url != null && !TextUtils.isEmpty(url)) {
            Glide.with(view.getContext())
                    .load(BuildConfig.MEDIA_URL + "/v1/file/download" + url)
                    .error(R.drawable.background_account)
                    //.placeholder(R.drawable.car)
                    .into(view);
        }
    }

    @BindingAdapter("android:src")
    public static void setImageUrl(ImageView view, int url) {
        view.setImageResource(url);
    }
}
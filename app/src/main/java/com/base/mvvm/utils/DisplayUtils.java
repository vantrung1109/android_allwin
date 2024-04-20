package com.base.mvvm.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class DisplayUtils {

    private DisplayUtils(){
        //do not initial me
    }

    public static int px2dp(float pxValue, Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
    public static int dp2px(float dipValue, Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2sp(float pxValue, Context context) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static int sp2px(float spValue, Context context) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static void showSoftInput(Context context) {
        InputMethodManager imm =
                (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE); // 显示软键盘
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static void showSoftInput(Context context, View view) {
        InputMethodManager imm =
                (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE); // 显示软键盘
        imm.showSoftInput(view, 0);
    }

    public static void hideSoftInput(Context context, View view) {
        InputMethodManager immHide =
                (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE); // 隐藏软键盘
        immHide.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    public static String custom_money(Double money) {
        int custom_money = money.intValue();
        String custom_money_string = "";
        while (custom_money / 1000 != 0){
            int temp =  custom_money % 1000;
            if (temp < 10){
                custom_money_string = ".00" + String.valueOf(temp) + custom_money_string;
            } else if (temp < 100){
                custom_money_string = ".0" + String.valueOf(temp) + custom_money_string;
            } else {
                custom_money_string = "." + String.valueOf(temp) + custom_money_string;
            }
            custom_money = custom_money / 1000;
        }
        custom_money_string = String.valueOf(custom_money) + custom_money_string + "đ";
        return custom_money_string;
    }
    public static String custom_money_discount(Double money) {
        int custom_money = money.intValue();
        String custom_money_string = "";
        while (custom_money / 1000 != 0){
            int temp =  custom_money % 1000;
            if (temp < 10){
                custom_money_string = ".00" + String.valueOf(temp) + custom_money_string;
            } else if (temp < 100){
                custom_money_string = ".0" + String.valueOf(temp) + custom_money_string;
            } else {
                custom_money_string = "." + String.valueOf(temp) + custom_money_string;
            }
            custom_money = custom_money / 1000;
        }
        custom_money_string = "Giảm " + String.valueOf(custom_money) + custom_money_string + "VND";
        return custom_money_string;
    }
    public static String custom_condition_money_discount(Double money) {
        int custom_money = money.intValue();
        String custom_money_string = "";
        while (custom_money / 1000 != 0){
            int temp =  custom_money % 1000;
            if (temp < 10){
                custom_money_string = ".00" + String.valueOf(temp) + custom_money_string;
            } else if (temp < 100){
                custom_money_string = ".0" + String.valueOf(temp) + custom_money_string;
            } else {
                custom_money_string = "." + String.valueOf(temp) + custom_money_string;
            }
            custom_money = custom_money / 1000;
        }
        custom_money_string = custom_money_string.substring(0, custom_money_string.length() - 4);
        custom_money_string = "Cước phí chỉ từ " + String.valueOf(custom_money) + custom_money_string + "k";
        return custom_money_string;
    }

}

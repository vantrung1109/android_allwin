package com.base.mvvm.ui.fragment.home.payment_method;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.mvvm.R;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.viewholders.FlexibleViewHolder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentMethodItem extends AbstractFlexibleItem<PaymentMethodItem.PaymentMethodViewHolder> {

    int id;
    int imgPaymentMethod;
    String paymentMethodName;
    boolean isSelected;

    public PaymentMethodItem(int imgPaymentMethod, String paymentMethodName, boolean isSelected) {
        this.imgPaymentMethod = imgPaymentMethod;
        this.paymentMethodName = paymentMethodName;
        this.isSelected = isSelected;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return false;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.rcv_item_payment_method;
    }

    @Override
    public PaymentMethodViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> flexibleAdapter) {
        return new PaymentMethodViewHolder(view, flexibleAdapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> flexibleAdapter, PaymentMethodViewHolder paymentMethodViewHolder, int i, List<Object> list) {
        paymentMethodViewHolder.imgPaymentMethod.setImageResource(imgPaymentMethod);
        paymentMethodViewHolder.tv_payment_method_name.setText(paymentMethodName);
        if(isSelected){
            paymentMethodViewHolder.imgCheck.setVisibility(View.VISIBLE);
        }else{
            paymentMethodViewHolder.imgCheck.setVisibility(View.GONE);
        }
    }

    public static class PaymentMethodViewHolder extends FlexibleViewHolder{

        ImageView imgPaymentMethod;
        TextView tv_payment_method_name;
        ImageView imgCheck;

        public PaymentMethodViewHolder(View view, FlexibleAdapter adapter) {
            super(view, adapter);
            imgPaymentMethod = view.findViewById(R.id.img_payment_method);
            tv_payment_method_name = view.findViewById(R.id.tv_payment_method_name);
            imgCheck = view.findViewById(R.id.img_is_selected);
        }
    }
}

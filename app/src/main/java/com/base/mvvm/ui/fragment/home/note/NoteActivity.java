package com.base.mvvm.ui.fragment.home.note;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.base.mvvm.R;
import com.base.mvvm.data.service.DatabaseService;
import com.base.mvvm.databinding.ActivityDiscountBinding;
import com.base.mvvm.databinding.ActivityNoteBinding;
import com.base.mvvm.di.component.ActivityComponent;
import com.base.mvvm.ui.base.BaseActivity;
import com.base.mvvm.ui.fragment.home.discount.DiscountViewModel;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.databinding.BR;

public class NoteActivity extends BaseActivity<ActivityNoteBinding, NoteViewModel> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_note;
    }

    @Override
    public int getBindingVariable() {
        return BR.vm;
    }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);
    }


}

package com.base.mvvm.ui.fragment.home.note;

import android.content.Intent;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            viewBinding.editNote.setText(bundle.getString("note"));
        }

        viewBinding.btnContinue.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("note", viewBinding.editNote.getText().toString());
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }

}

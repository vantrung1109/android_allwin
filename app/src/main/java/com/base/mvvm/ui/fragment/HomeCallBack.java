package com.base.mvvm.ui.fragment;

import java.util.List;

public interface HomeCallBack {
    void doSuccessGetData(Object data);

    void doSuccessGetData(List<Object> data);

    void doSuccess();
    void doError();
}

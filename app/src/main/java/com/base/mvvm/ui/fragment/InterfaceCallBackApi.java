package com.base.mvvm.ui.fragment;

public interface InterfaceCallBackApi<T> {
    void doSuccessGetData(T t);
    void doSuccess();
    void doError();
}

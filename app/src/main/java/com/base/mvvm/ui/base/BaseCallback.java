package com.base.mvvm.ui.base;

public interface BaseCallback {
    void doError(Throwable error);
    void doSuccess();
}

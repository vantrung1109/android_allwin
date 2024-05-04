package com.base.mvvm.data.websocket;

public interface SocketListener {
    void onMessage(SocketEventModel socketEventModel);
    void onTimeout(Message message);
    void onError();
    void onClosed();
    void onConnected();
    void onSessionExpire();
    void onPingFailure();
}

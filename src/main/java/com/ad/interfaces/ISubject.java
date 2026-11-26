package com.ad.interfaces;

public interface ISubject {
    void subscribe(IObserver observer);
    void unsubscribe(IObserver observer);
}
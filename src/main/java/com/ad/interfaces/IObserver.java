package com.ad.interfaces;

import java.util.List;

import sw.IStock;

public interface IObserver {
    void update(List<IStock> stocks);
}
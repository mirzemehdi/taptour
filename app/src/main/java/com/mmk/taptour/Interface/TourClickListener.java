package com.mmk.taptour.Interface;

import android.view.View;

import Model.Tour;

public interface TourClickListener {
    void onClick(View view, int position, Tour tour);
}

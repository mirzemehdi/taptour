package com.mmk.taptour.Interface;

import Model.Tour;

public interface LikeClickListener {
    boolean onClick(Tour tour, boolean isFavourite);
}

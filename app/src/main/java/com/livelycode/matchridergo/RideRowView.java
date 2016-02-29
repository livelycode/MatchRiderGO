package com.livelycode.matchridergo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.util.Date;

public class RideRowView extends LinearLayout {
    private String mDriverName;
    private String mCarName;
    private Date mDate;
    private String mStartLocation;
    private String mDestinationLocation;
    private float mDuration;
    private float mPrice;

    public RideRowView(Context context) {
        this(context, null);
    }

    public RideRowView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RideRowView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);
        if(!isInEditMode()) {
            inflate(getContext(), R.layout.sample_ride_row_view, this);
        }
    }
}
package com.livelycode.matchridergo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * TODO: document your custom view class.
 */
public class RideRowView extends LinearLayout {
    public RideRowView(Context context) {
        this(context, null);
    }

    public RideRowView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RideRowView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);
        if(!isInEditMode()) {

            //LayoutInflater inflater = (LayoutInflater) context
            //        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflate(getContext(), R.layout.sample_ride_row_view, this);

            //this.addView(inflater.inflate(R.layout.sample_ride_row_view, null));
        }
    }
}

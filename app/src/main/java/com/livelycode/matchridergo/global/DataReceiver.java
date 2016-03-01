package com.livelycode.matchridergo.global;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;

import com.livelycode.matchridergo.data.MatchRiderException;


/**
 * Created by konny on 29/02/16.
 */
public class DataReceiver extends ResultReceiver {
    private Receiver gReceiver;
    private static Creator CREATOR;
    private static final String TAG = DataReceiver.class.getSimpleName();

    public void setReceiver(Receiver receiver) {
        gReceiver = receiver;
    }

    /**
     * Create a new ResultReceive to receive results.  Your
     * {@link #onReceiveResult} method will be called from the thread running
     * <var>handler</var> if given, or from an arbitrary thread if null.
     *
     * @param handler
     */
    public DataReceiver(Handler handler) {
        super(handler);
    }

    public interface Receiver {
        void onReceiveResult(int statusCode, Bundle bundle) throws MatchRiderException;
    }

    @Override
    protected void onReceiveResult(int statusCode, Bundle bundle) {
        if (gReceiver != null) {
            try {
                gReceiver.onReceiveResult(statusCode, bundle);
            } catch (MatchRiderException e) {
                Log.d(TAG, "MatchRider MatchRiderError:" + e.getMessage());
            }
        }
    }
}

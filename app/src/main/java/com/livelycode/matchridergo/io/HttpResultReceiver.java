package com.livelycode.matchridergo.io;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

import com.livelycode.matchridergo.data.MatchRiderException;

import org.json.JSONException;

/**
 * Created by konny on 26/02/16.
 */
public class HttpResultReceiver extends ResultReceiver {
    private Receiver mReceiver;
    private static Creator CREATOR;

    public HttpResultReceiver(Handler handler) {
        super(handler);
    }

    public void setReceiver(Receiver receiver) {
        mReceiver = receiver;
    }

    public interface Receiver {
        void onReceiveResult(int resultCode, Bundle resultData) throws MatchRiderException, JSONException;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if (mReceiver != null) {
            try {
                mReceiver.onReceiveResult(resultCode, resultData);
            } catch (MatchRiderException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}

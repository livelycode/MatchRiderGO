package com.livelycode.matchridergo.global;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

import com.livelycode.matchridergo.data.MatchRiderError;
import com.livelycode.matchridergo.data.MatchRiderException;
import com.livelycode.matchridergo.data.Ride;
import com.livelycode.matchridergo.data.Session;
import com.livelycode.matchridergo.io.HttpConnector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by konny on 28/02/16.
 */
public class DataService extends IntentService{

    private static final String TAG = DataService.class.getSimpleName();

    public static final String ACCOUNT_DETAILS = "ACCOUNT_DETAILS";
    public static final String LOGIN = "LOGIN";
    public static final String BOOKED_RIDES = "BOOKED_RIDES";

    public static final int STATUS_RUNNING = 0;
    public static final int STATUS_FINISHED = 1;
    public static final int STATUS_ERROR = 2;


    public DataService() {
        super(TAG);
    }

    private Bundle parsedBookedRides() {
        JSONObject request = new JSONObject();

        try {
            request.put("userId", ClientState.getInstance().getSession().getUserId());
        } catch (JSONException e) {
            Log.d(TAG, "Booked Rides request JSON creation error: " + e.getMessage());
        }

        JSONArray jsonBookedRides = null;

        try {
            jsonBookedRides = new JSONArray(HttpConnector.post("/booked-rides", request.toString()));
        } catch (JSONException e) {
            Log.d(TAG, "JSON Array parsing MatchRiderError: " + e.getMessage());
        } catch (IOException e) {
            Log.d(TAG, "Http MatchRiderError: " + e.getMessage());
        }

        System.out.println(jsonBookedRides.toString());

        Ride[] rides = new Ride[jsonBookedRides.length()];
        for(int i=0; i < jsonBookedRides.length(); i++) {
            try {
                JSONObject jsonRide = jsonBookedRides.getJSONObject(i);
                rides[i] = new Ride(jsonRide);
            } catch (JSONException e) {
                Log.d(TAG, "Parsed Ride JSON MatchRiderError: " + e.getMessage());
            }
        }

        Bundle bundle = new Bundle();
        bundle.putParcelableArray("rides", rides);
        return bundle;
    }

    /**
     * Send LOGIN request to backend, parse JSON response session and create Session Object
     * @param email Email request data
     * @param password Password request data
     * @return session New Session Object
     */
    private Bundle parsedLogin(String email, String password) {
        JSONObject request = new JSONObject();
        try {
            request.put("email", email);
            request.put("password", password);
        } catch (JSONException e) {
            Log.d(TAG, "JSON creation errors: " + e.getMessage());
        }

        JSONObject response = null;

        try {
            response = new JSONObject(HttpConnector.post("/login", request.toString()));
        } catch (JSONException e) {
            Log.d(TAG, "JSON parsing error: " + e.getMessage());
        } catch (IOException e) {
            Log.d(TAG, "Http MatchRiderError: " + e.getMessage());
        }

        Bundle bundle = new Bundle();

        if(response.has("error")) {
            MatchRiderError error = new MatchRiderError(response);
            bundle.putParcelable("error", error);
        } else {
            Session session = null;
            try {
                session = new Session(response);
            } catch (MatchRiderException e) {
                Log.d(TAG, "Session Object MatchRiderError: " + e.getMessage());
            } catch (JSONException e) {
                Log.d(TAG, "JSON Parsing MatchRiderError: " + e.getMessage());
            }
            ClientState.getInstance().setSession(session);
            bundle.putParcelable("session", session);
        }
        return bundle;
    }

    @Override
    /**
     * Dispatches incoming requests from activities, contacts http service if necessary
     */
    protected void onHandleIntent(Intent intent) {
        ResultReceiver receiver = intent.getParcelableExtra("receiver");
        String request = intent.getStringExtra("request");
        receiver.send(STATUS_RUNNING, Bundle.EMPTY);

        switch (request) {
            case BOOKED_RIDES:
                receiver.send(STATUS_FINISHED, parsedBookedRides());
                break;
            case LOGIN:
                receiver.send(STATUS_FINISHED, parsedLogin(intent.getStringExtra("email"),
                        intent.getStringExtra("password")));
                break;
        }
    }
}

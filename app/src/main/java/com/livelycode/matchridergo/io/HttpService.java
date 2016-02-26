package com.livelycode.matchridergo.io;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by konny on 26/02/16.
 */
public class HttpService extends IntentService{

    private static final String TAG = HttpService.class.getSimpleName();

    private static final String SERVER_ADDRESS = "http://46.101.130.96:3000";

    public static final int STATUS_RUNNING = 0;
    public static final int STATUS_FINISHED = 1;
    public static final int STATUS_ERROR = 2;

    public HttpService() {
        super(TAG);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "Http Service started");

        final ResultReceiver receiver = intent.getParcelableExtra("receiver");
        String endpoint = intent.getStringExtra("endpoint");

        Bundle bundle = new Bundle();

        if (!TextUtils.isEmpty(endpoint)) {
            receiver.send(STATUS_RUNNING, Bundle.EMPTY);
        }

        try {
            JSONObject requestData = new JSONObject();
            requestData.put("email", intent.getStringExtra("email"));
            requestData.put("password", intent.getStringExtra("password"));
            String result = httpPost(endpoint, requestData.toString());

            if (null != result) {
                bundle.putString("result", result);
                receiver.send(STATUS_FINISHED, bundle);
            }
        } catch (Exception e) {
            bundle.putString(Intent.EXTRA_TEXT, e.toString());
            receiver.send(STATUS_ERROR, bundle);
        }
    }

    /**
     * Create server connection to endpoint and makes POST call
     * @param endpoint API endpoint
     * @return response body
     */
    private String httpPost(String endpoint, String requestData) throws IOException {

        String requestUrl = SERVER_ADDRESS + endpoint;
        InputStream inputStream = null;
        HttpURLConnection urlConnection = null;
        URL url = new URL(requestUrl);

        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setDoOutput(true);
        urlConnection.setRequestProperty("Content-type", "application/json; charset=UTF-8");
        urlConnection.setRequestProperty("Accept", "application/json");
        urlConnection.setRequestMethod("POST");

        byte[] outputBytes = requestData.getBytes("UTF-8");
        OutputStream os = urlConnection.getOutputStream();
        os.write(outputBytes);
        os.flush();

        if (urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + urlConnection.getResponseCode());
        }

        inputStream = new BufferedInputStream(urlConnection.getInputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        String response = convertInputStreamToString(in);
        urlConnection.disconnect();
        return response;
    }

    /**
     * Converts input stream from server to String
     * @param in buffered input stream
     * @return server response as string
     * @throws IOException
     */
    private String convertInputStreamToString(BufferedReader in) throws IOException {
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        return response.toString();
    }
}

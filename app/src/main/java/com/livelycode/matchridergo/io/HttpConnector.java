package com.livelycode.matchridergo.io;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by konny on 29/02/16.
 */
public class HttpConnector {
    private static final String TAG = HttpConnector.class.getSimpleName();

    public static final String SERVER_ADDRESS = "http://46.101.130.96:3000";
    public static final String LOCAL_ADDRESS = "http://192.168.0.20:3000";

    /**
     * Create server connection to endpoint and makes POST call
     * @param endpoint API endpoint
     * @return response body
     */
    public static String post(String endpoint, String requestData) throws IOException {
        String requestUrl = LOCAL_ADDRESS + endpoint;
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
    private static String convertInputStreamToString(BufferedReader in) throws IOException {
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        return response.toString();
    }
}

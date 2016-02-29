package com.livelycode.matchridergo.data;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

final public class Ride implements IMatchRiderObject {
    private Driver driver;
    private Car car;
    private Date date;
    private String startLocation;
    private String destinationLocation;
    private double distance;
    private int duration;
    private int[] price = new int[2];

    Calendar cal = Calendar.getInstance();

    public Ride(Driver driver,
                Car car,
                Date date,
                String startLocation,
                String destinationLocation,
                double distance,
                int duration,
                int[] price) {
        this.driver = driver;
        this.car = car;
        this.date = date;
        this.startLocation = startLocation;
        this.destinationLocation = destinationLocation;
        this.distance = distance;
        this.duration = duration;
        this.price[0] = price[0];
        this.price[1] = price[1];

        cal.setTime(this.date);
    }

    /*public Ride(HashMap<String, Object> rideConfiguration) {
        this.driver = (Driver) rideConfiguration.get("driver");
        this.car = (Car) rideConfiguration.get("car");
        this.date = (GregorianCalendar) rideConfiguration.get("date");
        this.startLocation = (String) rideConfiguration.get("startLocation");
        this.destinationLocation = (String) rideConfiguration.get("destinationLocation");
        this.distance = (double) rideConfiguration.get("distance");
        this.duration = (double) rideConfiguration.get("duration");
        this.price = (double) rideConfiguration.get("price");
    }*/

    public Ride(JSONObject json) throws JSONException, MatchRiderException {
        String[] requiredFields = {
                "driver",
                "car",
                "date",
                "startLocation",
                "destinationLocation",
                "distance",
                "duration",
                "price"
        };

        for (String requiredField : requiredFields) {
            if (!json.has(requiredField)) {
                throw new MatchRiderException("Missing required JSON field in RIDE:" + requiredField);
            }
        }

        JSONArray JSONPrice = json.getJSONArray("price");
        int[] price = new int[] {JSONPrice.getInt(0), JSONPrice.getInt(1)};

        new Ride(new Driver(json.getJSONObject("driver")),
                 new Car(json.getJSONObject("car")),
                 new Date(json.getInt("date")),
                 json.getString("startLocation"),
                 json.getString("destinationLocation"),
                 json.getDouble("distance"),
                 json.getInt("duration"),
                 price
        );
    }

    public Driver getDriver() { return this.driver; }
    public Car getCar() { return this.car; }

    public String getDate() { return new SimpleDateFormat("dd. MMMM").format(this.cal.getTimeInMillis()); }
    public String getDepartureTime() { return new SimpleDateFormat("HH:mm").format(this.cal.getTimeInMillis()); }
    public String getArrivalTime() { return new SimpleDateFormat("HH:mm").format(this.cal.getTimeInMillis() + this.duration * 60 * 1000L); }
    public int getHours() { return this.cal.get(Calendar.HOUR); }
    public int getMinutes() { return this.cal.get(Calendar.MINUTE); }

    public String getStartLocation() { return this.startLocation; }
    public String getDestinationLocation() { return this.destinationLocation; }

    public double getDistance() { return this.distance; }
    public double getDuration() { return this.duration; }
    public int[] getPrice() { return this.price; }

    public String toJSONString() { return null; }
}


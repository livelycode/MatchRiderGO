package com.livelycode.matchridergo.data;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

final public class Ride implements IMatchRiderObject {
    private Driver driver;
    private Car car;
    private GregorianCalendar date;
    private String startLocation;
    private String destinationLocation;
    private double distance;
    private double duration;
    private double price;

    public Ride(Driver driver,
                Car car,
                GregorianCalendar date,
                String startLocation,
                String destinationLocation,
                double distance,
                double duration,
                double price) {
        this.driver = driver;
        this.car = car;
        this.date = date;
        this.startLocation = startLocation;
        this.destinationLocation = destinationLocation;
        this.distance = distance;
        this.duration = duration;
        this.price = price;
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

    /*public Ride(JSONObject json) throws JSONException, MatchRiderException {
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


        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+'z");

        new Ride(new Driver(json.getJSONObject("driver")),
                 new Car(json.getJSONObject("car")),
                 //new GregorianCalendar(j).getString("date"),
                 json.getString("startLocation"),
                 json.getString("destinationLocation"),
                 json.getDouble("duration"),
                 json.getDouble("price")
        );
    }*/

    public Driver getDriver() { return this.driver; }
    public Car getCar() { return this.car; }

    public String getDate() { return new SimpleDateFormat("dd. MMMM").format(this.date.getTime()); }
    public int getHours() { return this.date.get(GregorianCalendar.HOUR_OF_DAY); }
    public int getMinutes() { return this.date.get(GregorianCalendar.MINUTE); }

    public String getStartLocation() { return this.startLocation; }
    public String getDestinationLocation() { return this.destinationLocation; }

    public double getDistance() { return this.distance; }
    public double getDuration() { return this.duration; }
    public double getPrice() { return this.price; }

    public String toJSONString() { return null; }
}


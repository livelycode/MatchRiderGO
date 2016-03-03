package com.livelycode.matchridergo.data;

import android.os.Parcel;
import android.os.Parcelable;
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

final public class Ride implements IMatchRiderObject, Parcelable {
    private Driver driver;
    private Car car;
    private Date date;
    private MatchPoint startLocation;
    private MatchPoint destinationLocation;
    private double distance;
    private int duration;
    private int[] price = new int[2];

    private String TAG = Ride.class.getSimpleName();

    Calendar cal = Calendar.getInstance();

    public Ride(Driver driver,
                Car car,
                Date date,
                MatchPoint startLocation,
                MatchPoint destinationLocation,
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

    public Ride(JSONObject json) throws JSONException {
        String[] requiredFields = {
                "driver",
                "car",
                "date",
                "start",
                "destination",
                "distance",
                "duration",
                "price"
        };

        for (String requiredField : requiredFields) {
            if (!json.has(requiredField)) {
                Log.d(Ride.class.getSimpleName(), "Missing required JSON field in RIDE:" + requiredField);
            }
        }

        JSONArray JSONPrice = null;
        try {
            JSONPrice = json.getJSONArray("price");
        } catch (JSONException e) {
            Log.d(TAG, "price array");
        }

        int[] price = new int[2];
        try {
            price = new int[] {JSONPrice.getInt(0), JSONPrice.getInt(1)};
        } catch (JSONException e) {
            Log.d(TAG, "price object");
        }

        try {
            this.driver = new Driver(json.getJSONObject("driver"));
        } catch (MatchRiderException e) {
            Log.d(TAG, "driver" + e.getMessage());
        }

        try {
            this.car = new Car(json.getJSONObject("car"));
        } catch (MatchRiderException e) {
            Log.d(TAG, "car" + e.getMessage());
        }

        this.date = new Date(json.getInt("date"));

        try {
            this.startLocation = new MatchPoint(json.getJSONObject("start"));
        } catch (MatchRiderException e) {
            Log.d(TAG, "mp1");
        }
        try {
            this.destinationLocation = new MatchPoint(json.getJSONObject("destination"));
        } catch (MatchRiderException e) {
            Log.d(TAG, "mp2");
        }


        this.distance = json.getDouble("distance");
        this.duration = json.getInt("duration");
        this.price = price;
    }

    public Driver getDriver() { return this.driver; }
    public Car getCar() { return this.car; }

    public String getDate() { return new SimpleDateFormat("dd. MMMM").format(this.cal.getTimeInMillis()); }
    public String getDepartureTime() { return new SimpleDateFormat("HH:mm").format(this.cal.getTimeInMillis()); }
    public String getArrivalTime() { return new SimpleDateFormat("HH:mm").format(this.cal.getTimeInMillis() + this.duration * 60 * 1000L); }
    public int getHours() { return this.cal.get(Calendar.HOUR); }
    public int getMinutes() { return this.cal.get(Calendar.MINUTE); }

    public MatchPoint getStartLocation() { return this.startLocation; }
    public MatchPoint getDestinationLocation() { return this.destinationLocation; }

    public double getDistance() { return this.distance; }
    public double getDuration() { return this.duration; }
    public int[] getPrice() { return this.price; }

    public String toJSONString() { return null; }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.driver, 0);
        dest.writeParcelable(this.car, 0);
        dest.writeLong(date != null ? date.getTime() : -1);
        dest.writeParcelable(this.startLocation, 0);
        dest.writeParcelable(this.destinationLocation, 0);
        dest.writeDouble(this.distance);
        dest.writeInt(this.duration);
        dest.writeIntArray(this.price);
        dest.writeSerializable(this.cal);
    }

    protected Ride(Parcel in) {
        this.driver = in.readParcelable(Driver.class.getClassLoader());
        this.car = in.readParcelable(Car.class.getClassLoader());
        long tmpDate = in.readLong();
        this.date = tmpDate == -1 ? null : new Date(tmpDate);
        this.startLocation = in.readParcelable(MatchPoint.class.getClassLoader());
        this.destinationLocation = in.readParcelable(MatchPoint.class.getClassLoader());
        this.distance = in.readDouble();
        this.duration = in.readInt();
        this.price = in.createIntArray();
        this.cal = (Calendar) in.readSerializable();
    }

    public static final Creator<Ride> CREATOR = new Creator<Ride>() {
        public Ride createFromParcel(Parcel source) {
            return new Ride(source);
        }

        public Ride[] newArray(int size) {
            return new Ride[size];
        }
    };
}


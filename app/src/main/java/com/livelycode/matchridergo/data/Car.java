package com.livelycode.matchridergo.data;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Car implements IMatchRiderObject, Parcelable {
    private String name;
    private String color = "Grau";
    private String license = "HD";

    public Car(String name, String color, String license) {
        this.name = name;
        this.color = color;
        this.license = license;
    }

    public Car(HashMap<String, Object> carConfiguration) {
        this.name = (String) carConfiguration.get("name");
        this.color = (String) carConfiguration.get("color");
        this.license = (String) carConfiguration.get("license");
    }

    public Car(JSONObject json) throws JSONException, MatchRiderException {
        String[] requiredFields = {
                "name",
                "color",
                "license"
        };

        for (String requiredField : requiredFields) {
            if (!json.has(requiredField)) {
                throw new MatchRiderException("Missing required JSON field in CAR:" + requiredField);
            }
        }

        new Car(json.getString("name"),
                json.getString("color"),
                json.getString("license")
        );
    }

    public String getName() { return this.name; }
    public String getColor() { return this.color; }
    public String getLicense() { return this.license; }

    public String toJSONString() { return null; }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.color);
        dest.writeString(this.license);
    }

    protected Car(Parcel in) {
        this.name = in.readString();
        this.color = in.readString();
        this.license = in.readString();
    }

    public static final Parcelable.Creator<Car> CREATOR = new Parcelable.Creator<Car>() {
        public Car createFromParcel(Parcel source) {
            return new Car(source);
        }

        public Car[] newArray(int size) {
            return new Car[size];
        }
    };
}

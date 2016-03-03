package com.livelycode.matchridergo.data;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Car implements IMatchRiderObject, Parcelable {
    private String model;
    private String color = "Grau";
    private String license = "HD";
    private String photo;

    public Car(String model, String color, String license, String photo) {
        this.model = model;
        this.color = color;
        this.license = license;
        this.photo = photo;
    }

    public Car(JSONObject json) throws JSONException, MatchRiderException {
        String[] requiredFields = {
                "model",
                "color",
                "license",
                "photo"
        };

        for (String requiredField : requiredFields) {
            if (!json.has(requiredField)) {
                throw new MatchRiderException("Missing required JSON field in CAR:" + requiredField);
            }
        }

        this.model = json.getString("model");
        this.color = json.getString("color");
        this.license = json.getString("license");
        this.photo = json.getString("photo");
    }

    public String getModel() { return this.model; }
    public String getColor() { return this.color; }
    public String getLicense() { return this.license; }

    public String toJSONString() { return null; }

    public String getPhoto() {
        return photo;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.model);
        dest.writeString(this.color);
        dest.writeString(this.license);
        dest.writeString(this.photo);
    }

    protected Car(Parcel in) {
        this.model = in.readString();
        this.color = in.readString();
        this.license = in.readString();
        this.photo = in.readString();
    }

    public static final Creator<Car> CREATOR = new Creator<Car>() {
        public Car createFromParcel(Parcel source) {
            return new Car(source);
        }

        public Car[] newArray(int size) {
            return new Car[size];
        }
    };
}

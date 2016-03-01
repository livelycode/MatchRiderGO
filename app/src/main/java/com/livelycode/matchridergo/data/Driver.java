package com.livelycode.matchridergo.data;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

final public class Driver implements IMatchRiderObject, Parcelable {
    private String name;
    private String description;
    private String email;
    private String id;
    private String photo;
    private String phone;
    private double score;

    public Driver(String name, String email, String photo, String description, String phone, String id, double score) {
        this.name = name;
        this.email = email;
        this.photo = photo;
        this.description = description;
        this.phone = phone;
        this.id = id;
        this.score = score;
    }

    public Driver(HashMap<String, Object> driverConfig) {
        new Driver((String) driverConfig.get("name"),
                   (String) driverConfig.get("email"),
                   (String) driverConfig.get("photo"),
                   (String) driverConfig.get("description"),
                   (String) driverConfig.get("phone"),
                   (String) driverConfig.get("id"),
                   (double) driverConfig.get("score"));
    }

    public Driver(JSONObject json) throws JSONException, MatchRiderException {
        String[] requiredFields = {
                "id",
                "name",
                "email",
                "photo",
                "score",
                "phone",
                "description"
        };

        for (String requiredField : requiredFields) {
            if (!json.has(requiredField)) {
                throw new MatchRiderException("Missing required JSON field in CAR:" + requiredField);
            }
        }

        new Driver(json.getString("name"),
                   json.getString("email"),
                   json.getString("photo"),
                   json.getString("description"),
                   json.getString("phone"),
                   json.getString("id"),
                   json.getDouble("score")
        );
    }

    public String getName() { return this.name; }
    public String getDescription() { return this.description; }

    public double getScore() { return this.score; }

    public String toJSONString() { return null; }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.email);
        dest.writeString(this.id);
        dest.writeString(this.photo);
        dest.writeString(this.phone);
        dest.writeDouble(this.score);
    }

    protected Driver(Parcel in) {
        this.name = in.readString();
        this.description = in.readString();
        this.email = in.readString();
        this.id = in.readString();
        this.photo = in.readString();
        this.phone = in.readString();
        this.score = in.readDouble();
    }

    public static final Parcelable.Creator<Driver> CREATOR = new Parcelable.Creator<Driver>() {
        public Driver createFromParcel(Parcel source) {
            return new Driver(source);
        }

        public Driver[] newArray(int size) {
            return new Driver[size];
        }
    };
}

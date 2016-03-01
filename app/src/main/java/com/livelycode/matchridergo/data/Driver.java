package com.livelycode.matchridergo.data;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

final public class Driver implements IMatchRiderObject, Parcelable {
    private String firstName;
    private String lastName;
    private String description;
    private String email;
    private String id;
    private String photo;
    private String phone;
    private double score;

    public Driver(String firstName, String lastName, String email, String photo, String description, String phone, String id, double score) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.photo = photo;
        this.description = description;
        this.phone = phone;
        this.id = id;
        this.score = score;
    }

    public Driver(HashMap<String, Object> driverConfig) {
        new Driver((String) driverConfig.get("firstName"),
                (String) driverConfig.get("lastName"),
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

        this.firstName = json.getString("firstName");
        this.lastName = json.getString("lastName");
        this.email = json.getString("email");
        this.photo = json.getString("photo");
        this.description = json.getString("description");
        this.phone = json.getString("phone");
        this.id = json.getString("id");
        this.score = json.getDouble("score");
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getDescription() {
        return this.description;
    }

    public double getScore() {
        return this.score;
    }

    public String toJSONString() {
        return null;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public String getPhoto() {
        return photo;
    }

    public String getPhone() {
        return phone;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeString(this.description);
        dest.writeString(this.email);
        dest.writeString(this.id);
        dest.writeString(this.photo);
        dest.writeString(this.phone);
        dest.writeDouble(this.score);
    }

    protected Driver(Parcel in) {
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.description = in.readString();
        this.email = in.readString();
        this.id = in.readString();
        this.photo = in.readString();
        this.phone = in.readString();
        this.score = in.readDouble();
    }

    public static final Creator<Driver> CREATOR = new Creator<Driver>() {
        public Driver createFromParcel(Parcel source) {
            return new Driver(source);
        }

        public Driver[] newArray(int size) {
            return new Driver[size];
        }
    };
}
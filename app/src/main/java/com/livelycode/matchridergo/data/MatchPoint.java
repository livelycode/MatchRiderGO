package com.livelycode.matchridergo.data;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by konny on 01/03/16.
 */
public class MatchPoint implements IMatchRiderObject, Parcelable {

    private String name;
    private String id;
    private String latitude;
    private String longitude;
    private String photo;

    public MatchPoint(String name, String id, String latitude,String longitude, String photo) {
        this.name = name;
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.photo = photo;
    }

    public MatchPoint(JSONObject jsonObject) throws MatchRiderException, JSONException {
        String[] requiredFields = {
                "name",
                "id",
                "latitude",
                "longitude",
                "photo"
        };

        for (String requiredField : requiredFields) {
            if (!jsonObject.has(requiredField)) {
                throw new MatchRiderException("Missing required JSON field in MATCHPOINT:" + requiredField);
            }
        }

        this.name = jsonObject.getString("name");
        this.id = jsonObject.getString("id");
        this.latitude = jsonObject.getString("latitude");
        this.longitude = jsonObject.getString("longitude");
        this.photo = jsonObject.getString("photo");
    }

    public String getId() {
        return id;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getPhoto() {
        return photo;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toJSONString() {
        return null;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.id);
        dest.writeString(this.latitude);
        dest.writeString(this.longitude);
        dest.writeString(this.photo);
    }

    protected MatchPoint(Parcel in) {
        this.name = in.readString();
        this.id = in.readString();
        this.latitude = in.readString();
        this.longitude = in.readString();
        this.photo = in.readString();
    }

    public static final Parcelable.Creator<MatchPoint> CREATOR = new Parcelable.Creator<MatchPoint>() {
        public MatchPoint createFromParcel(Parcel source) {
            return new MatchPoint(source);
        }

        public MatchPoint[] newArray(int size) {
            return new MatchPoint[size];
        }
    };
}

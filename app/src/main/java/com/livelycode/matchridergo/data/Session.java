package com.livelycode.matchridergo.data;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by konny on 26/02/16.
 */
public class Session implements  IMatchRiderObject, Parcelable {
    private String id;
    private String userId;

    public Session(String id, String userId) {
        this.id = id;
        this.userId = userId;
    }

    public Session(JSONObject jsonObject) throws MatchRiderException, JSONException {
        String[] requiredFields = {
                "id",
                "userId"
        };

        for (String requiredField : requiredFields) {
            if (!jsonObject.has(requiredField)) {
                throw new MatchRiderException("Missing required JSON field in SESSION:" + requiredField);
            }
        }

        this.id = jsonObject.getString("id");
        this.userId = jsonObject.getString("userId");
    }

    public String getUserId() {
        return userId;
    }

    public String getId() {
        return id;
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
        dest.writeString(this.id);
        dest.writeString(this.userId);
    }

    protected Session(Parcel in) {
        this.id = in.readString();
        this.userId = in.readString();
    }

    public static final Parcelable.Creator<Session> CREATOR = new Parcelable.Creator<Session>() {
        public Session createFromParcel(Parcel source) {
            return new Session(source);
        }

        public Session[] newArray(int size) {
            return new Session[size];
        }
    };
}

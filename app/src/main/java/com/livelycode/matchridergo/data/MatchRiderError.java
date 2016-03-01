package com.livelycode.matchridergo.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by konny on 29/02/16.
 */
public class MatchRiderError implements IMatchRiderObject, Parcelable {
    private String message;

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
        dest.writeString(this.message);
    }

    public MatchRiderError(String message) {
        this.message = message;
    }

    public MatchRiderError(JSONObject jsonObject) {
        String error = "";
        try {
            error = jsonObject.getString("error");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.message = error;
    }

    protected MatchRiderError(Parcel in) {
        this.message = in.readString();
    }

    public static final Parcelable.Creator<MatchRiderError> CREATOR = new Parcelable.Creator<MatchRiderError>() {
        public MatchRiderError createFromParcel(Parcel source) {
            return new MatchRiderError(source);
        }

        public MatchRiderError[] newArray(int size) {
            return new MatchRiderError[size];
        }
    };

    public String getMessage() {
        return this.message;
    }
}

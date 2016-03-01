package com.livelycode.matchridergo.data;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class User implements IMatchRiderObject, Parcelable {

    private String firstName;
    private String lastName;
    private String email;
    private String photo;
    private String description;
    private String phone;
    private String id;
    private String gender;
    private String facebookId;

    public User(String firstName,
                String lastName,
                String email,
                String photo,
                String description,
                String phone,
                String id,
                String gender,
                String facebookId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.photo = photo;
        this.description = description;
        this.phone = phone;
        this.id = id;
        this.gender = gender;
        this.facebookId = facebookId;
    }

    public User(JSONObject json) throws JSONException, MatchRiderException {
        String[] requiredFields = {
                "firstName",
                "lastName",
                "email",
                "photo",
                "description",
                "phone",
                "id",
                "gender",
                "facebookId"
        };

        for (String requiredField : requiredFields) {
            if (!json.has(requiredField)) {
                throw new MatchRiderException("Missing required JSON field in USER:" + requiredField);
            }
        }

        new User(json.getString("firstName"),
                json.getString("lastName"),
                json.getString("email"),
                json.getString("photo"),
                json.getString("description"),
                json.getString("phone"),
                json.getString("id"),
                json.getString("gender"),
                json.getString("facebookId")
        );
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoto() {
        return photo;
    }

    public String getDescription() {
        return description;
    }

    public String getPhone() {
        return phone;
    }

    public String getId() {
        return id;
    }

    public String getGender() {
        return gender;
    }

    public String getFacebookId() {
        return facebookId;
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
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeString(this.email);
        dest.writeString(this.photo);
        dest.writeString(this.description);
        dest.writeString(this.phone);
        dest.writeString(this.id);
        dest.writeString(this.gender);
        dest.writeString(this.facebookId);
    }

    protected User(Parcel in) {
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.email = in.readString();
        this.photo = in.readString();
        this.description = in.readString();
        this.phone = in.readString();
        this.id = in.readString();
        this.gender = in.readString();
        this.facebookId = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };
}

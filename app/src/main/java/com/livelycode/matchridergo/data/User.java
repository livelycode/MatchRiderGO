package com.livelycode.matchridergo.data;

import org.json.JSONException;
import org.json.JSONObject;

public class User implements IMatchRiderObject {

    private String name;
    private String email;
    private String photo;
    private String description;
    private String phone;
    private String id;
    private String gender;
    private String facebookId;

    public User(String name,
                String email,
                String photo,
                String description,
                String phone,
                String id,
                String gender,
                String facebookId) {
        this.name = name;
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
                "name",
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

        new User(json.getString("name"),
                json.getString("email"),
                json.getString("photo"),
                json.getString("description"),
                json.getString("phone"),
                json.getString("id"),
                json.getString("gender"),
                json.getString("facebookId")
        );
    }

    public String getName() {
        return name;
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
}

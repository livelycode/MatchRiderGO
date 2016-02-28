package com.livelycode.matchridergo.data;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

final public class Driver implements IMatchRiderObject  {
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
}

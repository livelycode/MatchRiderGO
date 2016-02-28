package com.livelycode.matchridergo.data;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Car implements IMatchRiderObject {
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
}

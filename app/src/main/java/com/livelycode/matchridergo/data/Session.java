package com.livelycode.matchridergo.data;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by konny on 26/02/16.
 */
public class Session implements  IMatchRiderObject{
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

        new Session(
                jsonObject.getString("id"),
                jsonObject.getString("userId")
        );
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
}

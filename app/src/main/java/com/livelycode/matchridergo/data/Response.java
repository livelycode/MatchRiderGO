package com.livelycode.matchridergo.data;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by konny on 26/02/16.
 */
public class Response {
    private final String errorMessage;
    private final IMatchRiderObject data;

    public Response(String serverResponse) throws JSONException, MatchRiderException {
        JSONObject responseOj = new JSONObject(serverResponse);
        if(responseOj.has("error")) {
            this.errorMessage = responseOj.getString("error");
        } else {
            this.errorMessage = null;
        }
        this.data = MatchRiderObjectFactory.fromJSON(responseOj);
    }

    public Boolean hasError() {
        return errorMessage != null && !errorMessage.isEmpty();
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public IMatchRiderObject getData() {
        return data;
    }
}

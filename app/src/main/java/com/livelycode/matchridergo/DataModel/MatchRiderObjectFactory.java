package com.livelycode.matchridergo.DataModel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by konny on 26/02/16.
 */
public class MatchRiderObjectFactory {
   private final static String USER = "USER";


   public static IMatchRiderObject fromJSON(JSONObject jsonObject) throws JSONException, MatchRiderException {
      if(jsonObject.has("type")) {
         switch (jsonObject.getString("type")) {
            case USER:
               return new User(jsonObject.getJSONObject("data"));
            default:
               return null;
         }
      } else {
         throw new MatchRiderException("Type not found");
      }
   }
}

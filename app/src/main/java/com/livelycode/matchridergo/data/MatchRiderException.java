package com.livelycode.matchridergo.data;

/**
 * Created by konny on 26/02/16.
 */
public class MatchRiderException extends Exception {
    String message = "";

    public MatchRiderException(String message) {
        this.message = message;
    }
}
package com.livelycode.matchridergo.data;

final public class Driver {
    private static int numberOfDrivers;

    public final String name;
    public final int score;

    Driver () {
        numberOfDrivers++;

        name = "Peter Hans " + String.valueOf(numberOfDrivers);
        score = 5;
    }
}
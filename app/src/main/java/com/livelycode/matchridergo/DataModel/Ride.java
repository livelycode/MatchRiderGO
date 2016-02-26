package com.livelycode.matchridergo.DataModel;

import java.util.GregorianCalendar;

final public class Ride {
    private static int numberOfRides;

    public final Driver driver = new Driver();
    private  String carName;
    private final GregorianCalendar date = new GregorianCalendar(115, 2, 29, 10, 50, 0);
    public  String startLocation = "Heidelberg";
    public  String destinationLocation = "Mannheim";
    private  double duration;
    private  double price;

    public Ride () {
        numberOfRides++;

        carName = "Peugot " + String.valueOf(numberOfRides);

        if(numberOfRides % 2 == 1) {
            startLocation = "Mannheim";
        } else {
            startLocation = "Heidelberg";
        }

        duration = 20 + numberOfRides;
        price = 3.5 + numberOfRides;
    }
}


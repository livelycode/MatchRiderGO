package com.livelycode.matchridergo;

import com.livelycode.matchridergo.data.Car;
import com.livelycode.matchridergo.data.Driver;
import com.livelycode.matchridergo.data.Ride;
import com.livelycode.matchridergo.data.Session;
import com.livelycode.matchridergo.data.User;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 * Created by konny on 26/02/16.
 */
public class GlobalData {
    private User user = new User("Eve", "eve@livelycode.com", "/assets/images/eve.jpg", "I like riding!", "555-1234", "11", "female", "4321");

    private Session session;
    private static final GlobalData globalData = new GlobalData();

    private Ride[] rides;
    private Driver[] drivers;
    private Car[] cars;

    public GlobalData() {
        rides = new Ride[3];
        drivers = new Driver[3];
        cars = new Car[3];

        cars[0] = new Car("Peugot", "grau", "HD 1");
        cars[1] = new Car("VW", "blau", "MA 3");
        cars[2] = new Car("Benz", "weiß", "F 2");

        drivers[0] = new Driver("Peter Hans", "peter@hans.de", "/photo.jpg", "I would love to take a Rhydon you", "+88123 098", "ab123", 1);
        drivers[1] = new Driver("Gustavo", "gustavo@web.de", "/gustavo.jpg", "Driving is my passion", "+65 312465", "aasd123", 3);
        drivers[2] = new Driver("Franz", "franz@gmx.de", "/franz.jpg", "Benz benz benz", "00564", "asd123", 5);

        int[] price = new int[2];
        price[0] = 2;
        price[1] = 40;

        rides[0] = new Ride(drivers[0], cars[0], new Date(1456750723000L), "Heidelberg", "Mannheim", 22, 40, price);
        rides[1] = new Ride(drivers[1], cars[1], new Date(1453750723000L), "Mannheim", "Mannheim", 22, 20, new int[]{3, 80});
        rides[2] = new Ride(drivers[2], cars[2], new Date(1652743428000L), "Heidelberg", "Heidelberg", 22, 15, new int[]{5, 40});
    }

    public static GlobalData getInstance() {
        return globalData;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Session getSession() {
        return session;
    }
    public void setSession(Session session) {
        this.session = session;
    }

    public Ride[] getRides() { return rides; }
}

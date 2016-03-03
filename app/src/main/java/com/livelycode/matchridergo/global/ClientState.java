package com.livelycode.matchridergo.global;

import android.content.Intent;

import com.livelycode.matchridergo.data.Car;
import com.livelycode.matchridergo.data.Driver;
import com.livelycode.matchridergo.data.Ride;
import com.livelycode.matchridergo.data.Session;
import com.livelycode.matchridergo.data.User;

import java.util.Date;

/**
 * Created by konny on 26/02/16.
 */
public class ClientState {

    private User user = new User("Eve", "Livelycode", "eve@livelycode.com", "/assets/images/eve.jpg", "I like riding!", "555-1234", "11", "female", "4321");

    private Session session;
    private static final ClientState CLIENT_STATE = new ClientState();

    public void setRides(Ride[] rides) {
        this.rides = rides;
    }

    public Driver[] getDrivers() {
        return drivers;
    }

    public void setDrivers(Driver[] drivers) {
        this.drivers = drivers;
    }

    public Car[] getCars() {
        return cars;
    }

    public void setCars(Car[] cars) {
        this.cars = cars;
    }

    private Ride[] rides;
    private Driver[] drivers;
    private Car[] cars;

    public ClientState() {
        session = new Session("ab12", "11");
        /*rides = new Ride[3];
        drivers = new Driver[3];
        cars = new Car[3];

        cars[0] = new Car("Peugot", "grau", "HD 1");
        cars[1] = new Car("VW", "blau", "MA 3");
        cars[2] = new Car("Benz", "weiß", "F 2");

        drivers[0] = new Driver("Peter", "Hans", "peter@hans.de", "/photo.jpg", "I would love to take a Rhydon you", "+88123 098", "ab123", 1);
        drivers[1] = new Driver("Gustavo", "Gustav", "gustavo@web.de", "/gustavo.jpg", "Driving is my passion", "+65 312465", "aasd123", 3);
        drivers[2] = new Driver("Franz", "Ganz", "franz@gmx.de", "/franz.jpg", "Benz benz benz", "00564", "asd123", 5);

        int[] price = new int[2];
        price[0] = 2;
        price[1] = 40;

        rides[0] = new Ride(drivers[0], cars[0], new Date(1456750723000L), "Heidelberg", "Mannheim", 22, 40, price);
        rides[1] = new Ride(drivers[1], cars[1], new Date(1453750723000L), "Mannheim", "Mannheim", 22, 20, new int[]{3, 80});
        rides[2] = new Ride(drivers[2], cars[2], new Date(1652743428000L), "Heidelberg", "Heidelberg", 22, 15, new int[]{5, 40});*/
    }

    public static ClientState getInstance() {
        return CLIENT_STATE;
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

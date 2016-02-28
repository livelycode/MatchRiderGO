package com.livelycode.matchridergo;

import com.livelycode.matchridergo.data.Ride;
import com.livelycode.matchridergo.data.Session;
import com.livelycode.matchridergo.data.User;

/**
 * Created by konny on 26/02/16.
 */
public class GlobalData {
    private User user = new User("Eve", "eve@livelycode.com", "/assets/images/eve.jpg", "I like riding!", "555-1234", "11", "female", "4321");

    private Ride[] rides;

    private Session session;
    private static final GlobalData globalData = new GlobalData();

    public GlobalData() {
        rides = new Ride[2];

        for(int i = 0; i < 2; i++) {
            rides[i] = new Ride();
        }
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

    public void setRides(Ride[] rides) { this.rides = rides; }
}

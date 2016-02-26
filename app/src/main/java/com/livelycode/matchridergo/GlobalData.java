package com.livelycode.matchridergo;

import com.livelycode.matchridergo.data.Session;
import com.livelycode.matchridergo.data.User;

/**
 * Created by konny on 26/02/16.
 */
public class GlobalData {
    private User user = new User("Eve", "eve@livelycode.com", "/assets/images/eve.jpg", "I like riding!", "555-1234", "11", "female", "4321");

    private Session session;
    private static final GlobalData globalData = new GlobalData();

    public GlobalData() {
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
}

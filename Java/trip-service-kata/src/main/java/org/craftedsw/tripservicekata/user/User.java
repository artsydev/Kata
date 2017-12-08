package org.craftedsw.tripservicekata.user;


import org.craftedsw.tripservicekata.trip.Trip;
import org.omg.PortableInterceptor.USER_EXCEPTION;

import java.util.ArrayList;
import java.util.List;


public class User {

    private List<Trip> trips = new ArrayList<>();
    private List<User> friends = new ArrayList<>();

    public void addFriend(User user) {
        friends.add(user);
    }

    public void addTrip(Trip trip) {
        trips.add(trip);
    }

    public List<User> getFriends() {
        return friends;
    }

    public List<Trip> trips() {
        return trips;
    }

    public boolean isFriendsWith(User user) {
        return friends.contains(user);
    }

}

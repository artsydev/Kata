package org.craftedsw.tripservicekata.user;


import org.craftedsw.tripservicekata.trip.Trip;


public class TestUserBuilder {

    private User[] friends = new User[] {};
    private Trip[] trips = new Trip[] {};

    public static TestUserBuilder aUser() {
        return new TestUserBuilder();
    }

    public TestUserBuilder friendsWith(User... friends) {
        this.friends = friends;
        return this;
    }

    public TestUserBuilder withTripsTo(Trip... trips) {
        this.trips = trips;
        return this;
    }

    public User build() {
        User newUser = new User();
        addTripsToUser(newUser);
        addFriendsTo(newUser);
        return newUser;
    }

    private void addFriendsTo(User newUser) {
        for (User friend : friends) {
            newUser.addFriend(friend);
        }
    }

    private void addTripsToUser(User newUser) {
        for (Trip trip : trips) {
            newUser.addTrip(trip);
        }
    }

}

package org.craftedsw.tripservicekata.trip;


import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TripServiceShould {

    private static final User GUEST = null;
    private static final User REGISTERED_USER = new User();
    private static final User SOMEBODY_ELSE = new User();
    private static final Trip PHILADELPHIA = new Trip();
    private User loggedInUser;

    @Test public void
    validate_that_the_user_is_logged_in() {
        TestableTripService tripService = new TestableTripService();

        loggedInUser = GUEST;

        assertThrows(UserNotLoggedInException.class,
                     () -> tripService.getTripsByUser(loggedInUser));
    }

    @Test public void
    not_return_any_trips_when_users_are_not_friends() {
        TestableTripService tripService = new TestableTripService();

        loggedInUser = REGISTERED_USER;

        User stranger = new User();
        stranger.addFriend(SOMEBODY_ELSE);
        stranger.addTrip(PHILADELPHIA);

        List<Trip> trips = tripService.getTripsByUser(stranger);

        assertThat(trips.size(), is(0));
    }

    @Test public void
    return_trips_when_users_are_friends() {
        TestableTripService tripService = new TestableTripService();

        loggedInUser = REGISTERED_USER;

        User friend = new User();
        friend.addFriend(loggedInUser);
        friend.addFriend(SOMEBODY_ELSE);
        friend.addTrip(PHILADELPHIA);

        List<Trip> trips = tripService.getTripsByUser(friend);

        assertThat(trips.size(), is(1));
    }


    class TestableTripService extends TripService {

        @Override protected User loggedInUser() {
            return loggedInUser;
        }

        @Override protected List<Trip> tripsByUser(User user) {
            return user.trips();
        }
    }

}

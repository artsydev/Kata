package org.craftedsw.tripservicekata.trip;


import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.craftedsw.tripservicekata.user.TestUserBuilder.aUser;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TripServiceShould {

    private static final User GUEST = null;
    private static final User REGISTERED_USER = new User();
    private static final User SOMEBODY_ELSE = new User();
    private static final Trip PHILADELPHIA = new Trip();
    private static final Trip CALIFORNIA = new Trip();
    private static final Trip GEORGIA = new Trip();
    private TripService tripService;
    private User loggedInUser;

    @BeforeEach
    public void initialise() {
        tripService = new TestableTripService();
        loggedInUser = REGISTERED_USER;
    }

    @Test public void
    validate_that_the_user_is_logged_in() {
        loggedInUser = GUEST;

        assertThrows(UserNotLoggedInException.class,
                     () -> tripService.getTripsByUser(loggedInUser));
    }

    @Test public void
    not_return_any_trips_when_users_are_not_friends() {
        User stranger = aUser().friendsWith(SOMEBODY_ELSE)
                               .withTripsTo(PHILADELPHIA)
                               .build();

        List<Trip> trips = tripService.getTripsByUser(stranger);

        assertThat(trips.size(), is(0));
    }

    @Test public void
    return_trips_when_users_are_friends() {
        User friend = aUser().friendsWith(loggedInUser, SOMEBODY_ELSE)
                             .withTripsTo(PHILADELPHIA, GEORGIA, CALIFORNIA)
                             .build();

        List<Trip> trips = tripService.getTripsByUser(friend);

        assertThat(trips.size(), is(3));
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

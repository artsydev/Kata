package org.craftedsw.tripservicekata.trip;


import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class TripServiceShould {

    private static final User GUEST = null;
    private User loggedInUser;

    @Test public void
    validate_that_the_user_is_logged_in() {
        TestableTripService trip = new TestableTripService();
        loggedInUser = GUEST;

        assertThrows(UserNotLoggedInException.class, () -> trip.getTripsByUser(loggedInUser));
    }

    class TestableTripService extends TripService {

        @Override protected User loggedInUser() {
            return loggedInUser;
        }

    }

}

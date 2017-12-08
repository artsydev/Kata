package org.craftedsw.tripservicekata.trip;


import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class TripServiceShould {

    @Test public void
    throw_exception_if_user_is_not_logged_in() {
        TripServiceTest trip = new TripServiceTest();
        User guest = new User();

        assertThrows(UserNotLoggedInException.class, () -> trip.getTripsByUser(guest));
    }

    class TripServiceTest extends TripService {

        @Override protected User getLoggedUser() {
            return null;
        }

    }

}

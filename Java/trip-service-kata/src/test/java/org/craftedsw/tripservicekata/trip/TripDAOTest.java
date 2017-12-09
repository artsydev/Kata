package org.craftedsw.tripservicekata.trip;


import org.craftedsw.tripservicekata.exception.CollaboratorCallException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class TripDAOTest {

    @Test public void
    throw_an_exception_when_any_trips_are_returned() {
        assertThrows(CollaboratorCallException.class,
                     () -> new TripDAO().tripsBy(new User()));
    }

}

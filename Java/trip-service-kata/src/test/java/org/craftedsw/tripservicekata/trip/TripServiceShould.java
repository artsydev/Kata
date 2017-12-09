package org.craftedsw.tripservicekata.trip;


import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.craftedsw.tripservicekata.user.TestUserBuilder.aUser;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;


@RunWith(MockitoJUnitRunner.class)
public class TripServiceShould {

    private static final User GUEST = null;
    private static final User REGISTERED_USER = new User();
    private static final User SOMEBODY_ELSE = new User();
    private static final Trip PHILADELPHIA = new Trip();
    private static final Trip CALIFORNIA = new Trip();
    private static final Trip GEORGIA = new Trip();

    @Mock private TripDAO tripDAO;
    @InjectMocks @Spy private TripService tripService = new TripService();

    @Test(expected = UserNotLoggedInException.class) public void
    validate_that_the_user_is_logged_in() {
        tripService.getFriendsTrips(SOMEBODY_ELSE, GUEST);
    }

    @Test public void
    not_return_any_trips_when_users_are_not_friends() {
        User stranger = aUser().friendsWith(SOMEBODY_ELSE)
                               .withTripsTo(PHILADELPHIA)
                               .build();

        List<Trip> trips = tripService.getFriendsTrips(stranger, REGISTERED_USER);

        assertThat(trips.size(), is(0));
    }

    @Test public void
    return_trips_when_users_are_friends() {
        User friend = aUser().friendsWith(REGISTERED_USER, SOMEBODY_ELSE)
                             .withTripsTo(PHILADELPHIA, GEORGIA, CALIFORNIA)
                             .build();

        given(tripDAO.tripsBy(friend)).willReturn(friend.trips());

        List<Trip> trips = tripService.getFriendsTrips(friend, REGISTERED_USER);

        assertThat(trips.size(), is(3));
    }

}

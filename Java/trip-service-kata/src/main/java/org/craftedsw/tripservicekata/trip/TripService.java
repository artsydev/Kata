package org.craftedsw.tripservicekata.trip;


import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class TripService {

    @Autowired private TripDAO tripDAO;

    public List<Trip> getFriendsTrips(User somebody,
                                      User loggedInUser) throws UserNotLoggedInException {
        validate(loggedInUser);

        return (somebody.isFriendsWith(loggedInUser))
               ? tripsBy(somebody)
               : noTrips();
    }

    private void validate(User loggedInUser) {
        if (loggedInUser == null) {
            throw new UserNotLoggedInException();
        }
    }

    private ArrayList<Trip> noTrips() {
        return new ArrayList<>();
    }

    private List<Trip> tripsBy(User user) {
        return tripDAO.tripsBy(user);
    }

}

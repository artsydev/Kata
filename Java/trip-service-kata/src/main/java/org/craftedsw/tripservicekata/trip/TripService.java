package org.craftedsw.tripservicekata.trip;


import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

import java.util.ArrayList;
import java.util.List;


public class TripService {

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        User loggedUser = loggedInUser();
        if (loggedUser == null) {
            throw new UserNotLoggedInException();
        }

        return (user.isFriendsWith(loggedUser))
               ? tripsByUser(user)
               : new ArrayList<>();
    }

    protected User loggedInUser() {
        return UserSession.getInstance()
                          .getLoggedUser();
    }

    protected List<Trip> tripsByUser(User user) {
        return TripDAO.findTripsByUser(user);
    }

}

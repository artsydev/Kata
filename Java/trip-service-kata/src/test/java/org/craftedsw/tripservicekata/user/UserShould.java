package org.craftedsw.tripservicekata.user;


import org.junit.jupiter.api.Test;

import java.util.List;

import static org.craftedsw.tripservicekata.user.TestUserBuilder.aUser;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class UserShould {

    private static final User GIADA = new User();
    private static final User SOPHIE = new User();

    @Test public void
    tell_us_if_they_are_friends_with_another_user() {
        User gemma = aUser().friendsWith(GIADA, SOPHIE)
                            .build();

        assertThat(gemma.isFriendsWith(SOPHIE), is(true));
    }

    @Test public void
    tell_us_if_they_are_not_friends_with_another_user() {
        User gemma = aUser().friendsWith(GIADA)
                            .build();

        assertThat(gemma.isFriendsWith(SOPHIE), is(false));
    }

}

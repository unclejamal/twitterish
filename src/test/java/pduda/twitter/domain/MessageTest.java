package pduda.twitter.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MessageTest {

    private SocialNetworker networker;
    private SocialNetworker bob;
    private Message messagePostedByBob;

    @Before
    public void setUp() throws Exception {
        networker = new SocialNetworker("alice");
        bob = new SocialNetworker("bob");
        messagePostedByBob = new Message(bob, "content", new Date(1));
    }

    @Test
    public void tellsTheMessageHasBeenPostedBySocialNetworker() {
        assertThat(messagePostedByBob.hasBeenPostedBy(bob), is(true));
    }

    @Test
    public void tellsTheMessageHasNotBeenPostedByADifferentSocialNetworker() {
        assertThat(messagePostedByBob.hasBeenPostedBy(networker), is(false));
    }
}
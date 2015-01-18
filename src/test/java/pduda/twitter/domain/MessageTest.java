package pduda.twitter.domain;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MessageTest {

    @Test
    public void tellsTheMessageHasBeenPostedBySocialNetworker() {
        SocialNetworker bob = new SocialNetworker("bob");
        Message message = new Message(bob, "content", new Date(1));
        assertThat(message.hasBeenPostedBy(bob), is(true));
    }

    @Test
    public void tellsTheMessageHasNotBeenPostedByADifferentSocialNetworker() {
        SocialNetworker alice = new SocialNetworker("alice");
        SocialNetworker bob = new SocialNetworker("bob");
        Message message = new Message(bob, "content", new Date(1));
        assertThat(message.hasBeenPostedBy(alice), is(false));
    }
}
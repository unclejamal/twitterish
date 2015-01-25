package pduda.twitter.domain;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static pduda.twitter.util.ObjectMother.somePublicationDate;

public class MessageTest {

    private SocialNetworker alice;
    private SocialNetworker bob;
    private Message messagePostedByBob;

    @Before
    public void setUp() throws Exception {
        alice = new SocialNetworker("alice");
        bob = new SocialNetworker("bob");
        messagePostedByBob = new Message(bob, "content", somePublicationDate());
    }

    @Test
    public void tellsTheMessageHasBeenPostedBySocialNetworker() {
        assertThat(messagePostedByBob.hasBeenPostedBy(bob), is(true));
    }

    @Test
    public void tellsTheMessageHasNotBeenPostedByADifferentSocialNetworker() {
        assertThat(messagePostedByBob.hasBeenPostedBy(alice), is(false));
    }
}
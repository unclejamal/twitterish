package pduda.twitter.domain;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static pduda.twitter.util.ObjectMother.somePublicationDate;

public class MessageTest {

    private AccountName alice;
    private AccountName bob;
    private Message messagePostedByBob;

    @Before
    public void setUp() throws Exception {
        alice = new AccountName("alice");
        bob = new AccountName("bob");
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
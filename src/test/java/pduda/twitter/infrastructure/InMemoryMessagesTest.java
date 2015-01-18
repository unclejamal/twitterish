package pduda.twitter.infrastructure;

import org.junit.Before;
import org.junit.Test;
import pduda.twitter.domain.Message;
import pduda.twitter.domain.SocialNetworker;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class InMemoryMessagesTest {

    private InMemoryMessages messages;
    private SocialNetworker bob;

    @Before
    public void setUp() throws Exception {
        messages = new InMemoryMessages();
        bob = new SocialNetworker("bob");
    }

    @Test
    public void getsNoMessagesForSocialNetworkThatHasNotPostedYet() {
        List<Message> messagesForBob = messages.getMessagesFor(bob);

        assertThat(messagesForBob, is(org.hamcrest.Matchers.empty()));
    }
    @Test
    public void getsMessagesForSocialNetworkerThatHasAlreadyPosted() {
        Message bobsMessage1 = new Message(bob, "content 1", new Date(1));
        Message bobsMessage2 = new Message(bob, "content 2", new Date(2));
        messages.addMessage(bobsMessage1);
        messages.addMessage(bobsMessage2);

        List<Message> messagesForBob = messages.getMessagesFor(bob);

        assertThat(messagesForBob, equalTo(Arrays.asList(bobsMessage1, bobsMessage2)));
    }
}
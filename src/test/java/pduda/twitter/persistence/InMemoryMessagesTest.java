package pduda.twitter.persistence;

import org.junit.Before;
import org.junit.Test;
import pduda.twitter.domain.AccountName;
import pduda.twitter.domain.Message;

import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static pduda.twitter.util.ObjectMother.someDay;

public class InMemoryMessagesTest {

    private InMemoryMessages messages;
    private AccountName bob;

    @Before
    public void setUp() throws Exception {
        messages = new InMemoryMessages();
        bob = new AccountName("bob");
    }

    @Test
    public void getsNoMessagesForSocialNetworkThatHasNotPostedYet() {
        List<Message> messagesForBob = messages.getMessagesChronologicallyDescendingFor(bob);

        assertThat(messagesForBob, is(org.hamcrest.Matchers.empty()));
    }
    @Test
    public void getsMessagesNewestFirstForSocialNetworkerThatHasAlreadyPosted() {
        Message bobsMessage1 = new Message(bob, "content 1", someDay().atTime(12, 30).toInstant(ZoneOffset.UTC));
        Message bobsMessage2 = new Message(bob, "content 2", someDay().atTime(13, 45).toInstant(ZoneOffset.UTC));
        messages.addMessage(bobsMessage1);
        messages.addMessage(bobsMessage2);

        List<Message> messagesForBob = messages.getMessagesChronologicallyDescendingFor(bob);

        assertThat(messagesForBob, equalTo(Arrays.asList(bobsMessage2, bobsMessage1)));
    }

}
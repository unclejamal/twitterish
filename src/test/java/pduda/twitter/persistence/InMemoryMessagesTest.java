package pduda.twitter.persistence;

import org.junit.Before;
import org.junit.Test;
import pduda.twitter.domain.AccountName;
import pduda.twitter.domain.Message;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

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
}
package pduda.twitter.infrastructure;

import org.junit.Test;
import pduda.twitter.domain.Message;
import pduda.twitter.domain.SocialNetworker;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class InMemoryMessagesTest {
    @Test
    public void getsNoMessagesForSocialNetworkThatHasNotPostedYet() {
        InMemoryMessages messages = new InMemoryMessages();

        List<Message> messagesForBob = messages.getMessagesFor(new SocialNetworker("bob"));

        assertThat(messagesForBob, is(org.hamcrest.Matchers.empty()));
    }
}
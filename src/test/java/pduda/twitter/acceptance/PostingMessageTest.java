package pduda.twitter.acceptance;

import org.junit.Before;
import org.junit.Test;
import pduda.twitter.domain.AccountName;
import pduda.twitter.domain.Message;
import pduda.twitter.domain.Messages;
import pduda.twitter.domain.usecase.PostMessage;
import pduda.twitter.persistence.InMemoryMessages;
import pduda.twitter.util.FixedClock;

import static java.time.ZoneOffset.UTC;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static pduda.twitter.util.ObjectMother.someDay;

public class PostingMessageTest {

    private PostMessage postMessage;
    private Messages messages;
    private AccountName alice;
    private FixedClock clock;

    @Before
    public void setUp() throws Exception {
        alice = new AccountName("Alice");
        messages = new InMemoryMessages();
        clock = new FixedClock();

        postMessage = new PostMessage(messages, clock);
    }

    @Test
    public void showsEmptyTimelineOfSocialNetworkerWhenNoMessagesHaveBeenPosted() {
        clock.fixAt(someDay().atTime(10, 0).toInstant(UTC));

        postMessage.execute(alice, "ZOMG! I love cats!");

        assertThat(messages.getMessagesFor(alice), is(asList(
                new Message(alice, "ZOMG! I love cats!", someDay().atTime(10, 0).toInstant(UTC)))));
    }
}

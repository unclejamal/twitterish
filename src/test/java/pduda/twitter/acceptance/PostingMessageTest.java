package pduda.twitter.acceptance;

import org.junit.Before;
import org.junit.Test;
import pduda.twitter.domain.AccountName;
import pduda.twitter.domain.Message;
import pduda.twitter.domain.SocialNetworkers;
import pduda.twitter.domain.Timeline;
import pduda.twitter.domain.usecase.PostMessage;
import pduda.twitter.persistence.InMemorySocialNetworkers;
import pduda.twitter.util.FixedClock;

import static java.time.ZoneOffset.UTC;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static pduda.twitter.util.ObjectMother.someDay;

public class PostingMessageTest {

    private PostMessage postMessage;
    private SocialNetworkers socialNetworkers;
    private AccountName alice;
    private FixedClock clock;

    @Before
    public void setUp() throws Exception {
        alice = new AccountName("Alice");
        socialNetworkers = new InMemorySocialNetworkers();
        clock = new FixedClock();

        postMessage = new PostMessage(socialNetworkers, clock);
    }

    @Test
    public void showsEmptyTimelineOfSocialNetworkerWhenNoMessagesHaveBeenPosted() {
        clock.fixAt(someDay().atTime(10, 0).toInstant(UTC));

        postMessage.execute(alice, "ZOMG! I love cats!");

        assertThat(socialNetworkers.getSocialNetworker(alice).getPersonalTimeline(), is(new Timeline(asList(
                new Message(alice, "ZOMG! I love cats!", someDay().atTime(10, 0).toInstant(UTC))))));
    }
}

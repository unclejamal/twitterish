package pduda.twitter.acceptance;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;
import pduda.twitter.domain.AccountName;
import pduda.twitter.domain.Message;
import pduda.twitter.domain.SocialNetworkers;
import pduda.twitter.domain.Timeline;
import pduda.twitter.domain.usecase.ReadTimeline;
import pduda.twitter.persistence.InMemorySocialNetworkers;

import java.util.Arrays;
import java.util.List;

import static java.time.ZoneOffset.UTC;
import static org.hamcrest.MatcherAssert.assertThat;
import static pduda.twitter.util.ObjectMother.someDay;

public class ReadingTimelineTest {

    private ReadTimeline readTimeline;
    private SocialNetworkers socialNetworkers;
    private AccountName bob;
    private AccountName alice;

    @Before
    public void setUp() throws Exception {
        alice = new AccountName("Alice");
        bob = new AccountName("Bob");
        socialNetworkers = new InMemorySocialNetworkers();

        readTimeline = new ReadTimeline(socialNetworkers);
    }

    @Test
    public void showsTimelineWithMessagesPreviouslyAddedByASocialNetworker() {
        socialNetworkers.getOrCreateSocialNetworker(alice).postMessage(new Message(alice, "I love the weather today", someDay().atTime(9, 55).toInstant(UTC)));
        socialNetworkers.getOrCreateSocialNetworker(bob).postMessage(new Message(bob, "Damn! We lost!", someDay().atTime(9, 58).toInstant(UTC)));
        socialNetworkers.getOrCreateSocialNetworker(bob).postMessage(new Message(bob, "Good game though.", someDay().atTime(9, 59).toInstant(UTC)));

        Timeline timeline = readTimeline.execute(bob);
        assertThat(timeline, hasMessages(
                new Message(bob, "Good game though.", someDay().atTime(9, 59).toInstant(UTC)),
                new Message(bob, "Damn! We lost!", someDay().atTime(9, 58).toInstant(UTC))));
    }

    private Matcher<? super Timeline> hasMessages(final Message... messages) {
        List<Message> expectedMessages = Arrays.asList(messages);
        return new TypeSafeMatcher<Timeline>() {
            @Override
            protected boolean matchesSafely(Timeline timeline) {
                return timeline.containsAll(expectedMessages);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("the timeline to have messages: ");
                expectedMessages.stream()
                        .forEach(expectedMessage -> {
                            description.appendValue(expectedMessage);
                            description.appendText(", ");
                        });

            }
        };
    }

    private Matcher<Timeline> hasNoMessages() {
        return new TypeSafeMatcher<Timeline>() {
            @Override
            protected boolean matchesSafely(Timeline timeline) {
                return !timeline.hasMessages();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("the timeline to have no messages");
            }
        };
    }

}

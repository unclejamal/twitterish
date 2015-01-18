package pduda.twitter.acceptance;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;
import pduda.twitter.domain.*;
import pduda.twitter.infrastructure.InMemoryMessages;
import pduda.twitter.usecase.ReadTimeline;

import java.util.Arrays;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;

public class ReadingTimelineTest {

    private ReadTimeline readTimeline;
    private InMemoryMessages messages = new InMemoryMessages();
    private SocialNetworker bob;
    private SocialNetworker alice;

    @Before
    public void setUp() throws Exception {
        alice = new SocialNetworker("Alice");
        bob = new SocialNetworker("Bob");
        readTimeline = new ReadTimeline(messages);
    }

    @Test
    public void showsEmptyTimelineOfSocialNetworkerWhenNoMessagesHaveBeenPosted() {
        Timeline timeline = readTimeline.execute(alice);
        assertThat(timeline, hasNoMessages());
    }

    @Test
    public void showsTimelineWithMessagesPreviouslyAddedByASocialNetworker() {
        messages.addMessage(new Message(alice, "I love the weather today", new Date(1)));
        messages.addMessage(new Message(bob, "Damn! We lost!", new Date(2)));
        messages.addMessage(new Message(bob, "Good game though.", new Date(3)));
        Timeline timeline = readTimeline.execute(bob);
        assertThat(timeline, hasMessages(new Message(bob, "Damn! We lost!", new Date(2)), new Message(bob, "Good game though.", new Date(3))));
    }

    private Matcher<? super Timeline> hasMessages(final Message... messages) {
        return new TypeSafeMatcher<Timeline>() {
            @Override
            protected boolean matchesSafely(Timeline timeline) {
                return timeline.containsAll(Arrays.asList(messages));
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("the timeline to have messages: ");
                for (Message message : messages) {
                    description.appendValue(message);
                    description.appendText(", ");
                }
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

package pduda;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class ReadingTimelineTest {

    private ReadTimeline readTimeline;

    @Before
    public void setUp() throws Exception {
        readTimeline = new ReadTimeline();
    }

    @Test
    public void readingEmptyTimeline() {
        SocialNetworker alice = new SocialNetworker("Alice");
        Timeline timeline = readTimeline.execute(alice);
        assertThat(timeline, hasNoPosts());
    }

    private Matcher<Timeline> hasNoPosts() {
        return new TypeSafeMatcher<Timeline>() {
            @Override
            protected boolean matchesSafely(Timeline timeline) {
                return !timeline.hasPosts();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("the timeline to have no entries");
            }
        };
    }

    private static class ReadTimeline {

        public Timeline execute(SocialNetworker username) {
            return new Timeline();
        }
    }

    private static class Timeline {
        public boolean hasPosts() {
            return false;
        }
    }

    private static class SocialNetworker {
        public SocialNetworker(String username) {
            
        }
    }
}

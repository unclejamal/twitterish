package pduda.twitter.endtoend;

import org.junit.Test;

import static pduda.twitter.util.ObjectMother.someDay;

public class ReadingTimelineEndToEndTest extends EndToEndTest {

    @Test(timeout = 1000)
    public void postMessagesToTheTimeline() throws Exception {
        enter("Alice -> I love the weather today", whenTimeIs(someDay().atTime(9, 55)));
        enter("Bob -> Damn! We lost!", whenTimeIs(someDay().atTime(9, 58)));
        enter("Bob -> Good game though.", whenTimeIs(someDay().atTime(9, 59)));

        enter("Alice", whenTimeIs(someDay().atTime(10, 0)));
        assertOutputLines(
                "I love the weather today (5 minutes ago)"
        );

        enter("Bob");
        assertOutputLines(
                "Good game though. (1 minute ago)",
                "Damn! We lost! (2 minutes ago)"
        );

        enter("quit");
    }
}

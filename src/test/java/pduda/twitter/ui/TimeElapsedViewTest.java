package pduda.twitter.ui;

import org.junit.Before;
import org.junit.Test;
import pduda.twitter.util.FixedClock;

import java.time.LocalDateTime;

import static java.time.ZoneOffset.UTC;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static pduda.twitter.util.ObjectMother.someDay;

public class TimeElapsedViewTest {

    private FixedClock clock;
    private TimeElapsedView timeElapsedView;

    @Before
    public void setUp() {
        clock = new FixedClock();

        timeElapsedView = new TimeElapsedView(clock);
    }

    @Test
    public void presentsOneSecondAgo() {
        assertThat(timeElapsedBetween(someDay().atTime(10, 0), someDay().atTime(9, 59, 59)), is("1 second ago"));
    }

    @Test
    public void presentsTwoSecondsAgo() {
        assertThat(timeElapsedBetween(someDay().atTime(10, 0), someDay().atTime(9, 59, 58)), is("2 seconds ago"));
    }

    @Test
    public void presentsOneMinuteAgo() {
        assertThat(timeElapsedBetween(someDay().atTime(10, 0), someDay().atTime(9, 59, 0)), is("1 minute ago"));
    }

    @Test
    public void presentsTwoMinutesAgo() {
        assertThat(timeElapsedBetween(someDay().atTime(10, 0), someDay().atTime(9, 58, 0)), is("2 minutes ago"));
    }

    @Test
    public void presentsOneMinuteAgoForEverythingBetweenOneAndAlmostTwoMinutes() {
        assertThat(timeElapsedBetween(someDay().atTime(10, 0), someDay().atTime(9, 58, 30)), is("1 minute ago"));
    }


    private String timeElapsedBetween(LocalDateTime newerInstant, LocalDateTime olderInstant) {
        clock.fixAt(newerInstant.toInstant(UTC));
        return timeElapsedView.since(olderInstant.toInstant(UTC));
    }
}
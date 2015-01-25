package pduda.twitter.ui;

import org.junit.Before;
import org.junit.Test;

import java.time.Year;

import static java.time.Month.JANUARY;
import static java.time.ZoneOffset.UTC;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

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
        clock.fixAt(Year.of(2015).atMonth(JANUARY).atDay(30).atTime(10, 0).toInstant(UTC));
        String timeElapsed = timeElapsedView.since(Year.of(2015).atMonth(JANUARY).atDay(30).atTime(9, 59).toInstant(UTC));
        assertThat(timeElapsed, is("1 second ago"));
    }

}
package pduda.twitter.domain;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TimelineTest {
    @Test
    public void createsAnEmptyTimelineWhenMergingEmptyTimelines() {
        Timeline resultingTimeline = Timeline.empty().mergeWith(Timeline.empty());
        assertThat(resultingTimeline, is(Timeline.empty()));
    }
}
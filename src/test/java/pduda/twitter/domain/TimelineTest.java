package pduda.twitter.domain;

import org.junit.Before;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static pduda.twitter.util.ObjectMother.nonEmptyTimeline;
import static pduda.twitter.util.ObjectMother.somePublicationDate;

public class TimelineTest {

    private AccountName bob;

    @Before
    public void setUp() {
        bob = new AccountName("bob");
    }

    @Test
    public void createsAnEmptyTimelineWhenMergingEmptyTimelines() {
        Timeline resultingTimeline = Timeline.empty().mergeWith(Timeline.empty());
        assertThat(resultingTimeline, is(Timeline.empty()));
    }

    @Test
    public void createsTimelineWithMessagesOfNonEmptyTimelineWhenMergingWithEmptyTimeline() {
        Timeline resultingTimeline = nonEmptyTimeline().mergeWith(Timeline.empty());
        assertThat(resultingTimeline, is(nonEmptyTimeline()));
    }

    @Test
    public void createsChronologicallyReverseSortedTimeline() {
        Message bobsMessageAtTime0 = new Message(bob, "bobs content 1", somePublicationDate());
        Message bobsMessageAtTime2 = new Message(bob, "bobs content 2", somePublicationDate().plusSeconds(2));
        Timeline bobsTimeline = Timeline.withReverseChronologicalOrder(asList(
                bobsMessageAtTime0, bobsMessageAtTime2));

        Message alicesMessageAtTime1 = new Message(bob, "alices content 1", somePublicationDate().plusSeconds(1));
        Message alicesMessageAtTime3 = new Message(bob, "alices content 3", somePublicationDate().plusSeconds(3));
        Timeline alicesTimeline = Timeline.withReverseChronologicalOrder(asList(
                alicesMessageAtTime1, alicesMessageAtTime3));


        Timeline resultingTimeline = bobsTimeline.mergeWith(alicesTimeline);

        assertThat(resultingTimeline, is(new Timeline(asList(
                alicesMessageAtTime3, bobsMessageAtTime2, alicesMessageAtTime1, bobsMessageAtTime0
        ))));
    }
}
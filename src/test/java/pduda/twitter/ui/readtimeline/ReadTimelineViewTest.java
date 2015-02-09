package pduda.twitter.ui.readtimeline;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import pduda.twitter.domain.AccountName;
import pduda.twitter.domain.Message;
import pduda.twitter.domain.Timeline;
import pduda.twitter.ui.ConsoleOutput;
import pduda.twitter.ui.TimeElapsedView;

import java.time.Instant;

import static java.util.Arrays.asList;
import static pduda.twitter.util.ObjectMother.somePublicationDate;

public class ReadTimelineViewTest {

    private ConsoleOutput output;
    private ReadTimelineView view;
    private TimeElapsedView timeElapsedView;

    @Before
    public void setUp() throws Exception {
        output = Mockito.mock(ConsoleOutput.class);
        timeElapsedView = Mockito.mock(TimeElapsedView.class);

        view = new ReadTimelineView(output, timeElapsedView);
    }

    @Test
    public void doesNotOutputAnythingForAnEmptyTimeline() {
        view.present(Timeline.empty());
        Mockito.verifyZeroInteractions(output);
    }

    @Test
    public void outputsMessagesForNonEmptyTimelines() {
        Instant publicationDate = somePublicationDate();
        Mockito.when(timeElapsedView.since(publicationDate)).thenReturn("3 minutes ago");

        view.present(Timeline.withReverseChronologicalOrder(asList(
                new Message(new AccountName("bob"), "content", publicationDate))));

        Mockito.verify(output).writeLineAndFlush("content (3 minutes ago)");
    }
}
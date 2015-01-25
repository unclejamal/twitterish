package pduda.twitter.ui;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import pduda.twitter.domain.Message;
import pduda.twitter.domain.SocialNetworker;
import pduda.twitter.domain.Timeline;

import java.time.Instant;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

public class TheViewTest {

    private ConsoleOutput output;
    private TheView view;
    private TimeElapsedView timeElapsedView;

    @Before
    public void setUp() throws Exception {
        output = Mockito.mock(ConsoleOutput.class);
        timeElapsedView = Mockito.mock(TimeElapsedView.class);

        view = new TheView(output, timeElapsedView);
    }

    @Test
    public void doesNotOutputAnythingForAnEmptyTimeline() {
        view.present(new Timeline(emptyList()));
        Mockito.verifyZeroInteractions(output);
    }

    @Test
    public void outputsMessagesForNonEmptyTimelines() {
        // TODO fix now
        view.present(new Timeline(asList(new Message(new SocialNetworker("bob"), "content", Instant.now()))));
        Mockito.verify(output).writeLineAndFlush("content (5 minutes ago)");
    }

}
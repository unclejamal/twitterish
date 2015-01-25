package pduda.twitter.ui;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import pduda.twitter.domain.Message;
import pduda.twitter.domain.SocialNetworker;
import pduda.twitter.domain.Timeline;

import java.util.Date;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

public class TheViewTest {

    private ConsoleOutput output;
    private TheView view;

    @Before
    public void setUp() throws Exception {
        output = Mockito.mock(ConsoleOutput.class);
        view = new TheView(output);
    }

    @Test
    public void doesNotOutputAnythingForAnEmptyTimeline() {
        view.present(new Timeline(emptyList()));
        Mockito.verifyZeroInteractions(output);
    }

    @Test
    public void outputsMessagesForNonEmptyTimelines() {
        view.present(new Timeline(asList(new Message(new SocialNetworker("bob"), "content", new Date(1)))));
        Mockito.verify(output).writeLineAndFlush("content (5 minutes ago)");
    }

}
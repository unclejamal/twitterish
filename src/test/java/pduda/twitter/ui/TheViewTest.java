package pduda.twitter.ui;

import org.junit.Test;
import org.mockito.Mockito;
import pduda.twitter.domain.Message;
import pduda.twitter.domain.SocialNetworker;
import pduda.twitter.domain.Timeline;

import java.util.Date;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

public class TheViewTest {

    @Test
    public void doesNotOutputAnythingForAnEmptyTimeline() {
        ConsoleOutput output = Mockito.mock(ConsoleOutput.class);
        TheView view = new TheView(output);
        view.present(new Timeline(emptyList()));
        Mockito.verifyZeroInteractions(output);
    }

    @Test
    public void outputsMessagesForNonEmptyTimelines() {
        ConsoleOutput output = Mockito.mock(ConsoleOutput.class);
        TheView view = new TheView(output);
        view.present(new Timeline(asList(new Message(new SocialNetworker("bob"), "content", new Date(1)))));
        Mockito.verify(output).writeLineAndFlush("content (5 minutes ago)");
    }

}
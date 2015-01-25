package pduda.twitter.ui;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import pduda.twitter.domain.SocialNetworker;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ConsoleRouterTest {

    private static final SocialNetworker alice = new SocialNetworker("Alice");
    private ReadTimelineController readTimelineController;
    private PostMessageController postMessageController;
    private WallController wallController;
    private ArgumentCaptor<SocialNetworker> actualSocialNetworker;
    private ArgumentCaptor<String> actualMessage;

    private ConsoleRouter consoleRouter;

    @Before
    public void setUp(){
        readTimelineController = Mockito.mock(ReadTimelineController.class);
        postMessageController = Mockito.mock(PostMessageController.class);
        wallController = Mockito.mock(WallController.class);
        consoleRouter = new ConsoleRouter(readTimelineController, postMessageController, wallController);

        actualSocialNetworker = ArgumentCaptor.forClass(SocialNetworker.class);
        actualMessage = ArgumentCaptor.forClass(String.class);
    }

    @Test
    public void routesPostMessageCommands() {
        consoleRouter.routeCommand("Alice -> Hello!");

        Mockito.verify(postMessageController).execute(actualSocialNetworker.capture(), actualMessage.capture());
        assertThat(actualSocialNetworker.getValue(), is(alice));
        assertThat(actualMessage.getValue(), is("Hello!"));
    }

    @Test
    public void routesReadTimelineCommands() {
        consoleRouter.routeCommand("Alice");

        Mockito.verify(readTimelineController).execute(actualSocialNetworker.capture());
        assertThat(actualSocialNetworker.getValue(), is(alice));
    }

    @Test
    public void routesWallCommands() {
        consoleRouter.routeCommand("Alice wall");

        Mockito.verify(wallController).execute(actualSocialNetworker.capture());
        assertThat(actualSocialNetworker.getValue(), is(alice));
    }

}
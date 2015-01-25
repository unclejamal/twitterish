package pduda.twitter.ui;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import pduda.twitter.domain.SocialNetworker;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CompositeConsoleRouterTest {

    private static final SocialNetworker alice = new SocialNetworker("Alice");
    private static final SocialNetworker bob = new SocialNetworker("Bob");
    private ReadTimelineController readTimelineController;
    private PostMessageController postMessageController;
    private WallController wallController;
    private FollowController followController;
    private ArgumentCaptor<SocialNetworker> actualSocialNetworker;
    private ArgumentCaptor<String> actualMessage;

    private CompositeConsoleRouter compositeConsoleRouter;

    @Before
    public void setUp() {
        readTimelineController = Mockito.mock(ReadTimelineController.class);
        postMessageController = Mockito.mock(PostMessageController.class);
        wallController = Mockito.mock(WallController.class);
        followController = Mockito.mock(FollowController.class);
        compositeConsoleRouter = new CompositeConsoleRouter(
                readTimelineController,
                postMessageController,
                wallController,
                followController
        );

        actualSocialNetworker = ArgumentCaptor.forClass(SocialNetworker.class);
        actualMessage = ArgumentCaptor.forClass(String.class);
    }

    @Test
    public void routesPostMessageCommands() {
        compositeConsoleRouter.route("Alice -> Hello!");

        Mockito.verify(postMessageController).execute(actualSocialNetworker.capture(), actualMessage.capture());
        assertThat(actualSocialNetworker.getValue(), is(alice));
        assertThat(actualMessage.getValue(), is("Hello!"));
    }

    @Test
    public void routesReadTimelineCommands() {
        compositeConsoleRouter.route("Alice");

        Mockito.verify(readTimelineController).execute(actualSocialNetworker.capture());
        assertThat(actualSocialNetworker.getValue(), is(alice));
    }

    @Test
    public void routesWallCommands() {
        compositeConsoleRouter.route("Alice wall");

        Mockito.verify(wallController).execute(actualSocialNetworker.capture());
        assertThat(actualSocialNetworker.getValue(), is(alice));
    }

    @Test
    public void routesFollowCommands() {
        ArgumentCaptor<SocialNetworker> actualFollower = ArgumentCaptor.forClass(SocialNetworker.class);
        ArgumentCaptor<SocialNetworker> actualFollowee = ArgumentCaptor.forClass(SocialNetworker.class);

        compositeConsoleRouter.route("Alice follows Bob");

        Mockito.verify(followController).execute(actualFollower.capture(), actualFollowee.capture());
        assertThat(actualFollower.getValue(), is(alice));
        assertThat(actualFollowee.getValue(), is(bob));
    }
}
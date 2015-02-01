package pduda.twitter.ui;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import pduda.twitter.domain.AccountName;
import pduda.twitter.ui.follow.FollowController;
import pduda.twitter.ui.postmessage.PostMessageController;
import pduda.twitter.ui.readtimeline.ReadTimelineController;
import pduda.twitter.ui.wall.WallController;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CompositeConsoleRouterTest {

    private static final AccountName alice = new AccountName("Alice");
    private static final AccountName bob = new AccountName("Bob");
    private List allControllers = new ArrayList<>();
    private ReadTimelineController readTimelineController;
    private PostMessageController postMessageController;
    private WallController wallController;
    private FollowController followController;
    private ArgumentCaptor<AccountName> actualSocialNetworker;
    private ArgumentCaptor<String> actualMessage;
    private CompositeConsoleRouter compositeConsoleRouter;

    @Before
    public void setUp() {
        readTimelineController = addMock(ReadTimelineController.class);
        postMessageController = addMock(PostMessageController.class);
        wallController = addMock(WallController.class);
        followController = addMock(FollowController.class);

        compositeConsoleRouter = new CompositeConsoleRouter(
                readTimelineController,
                postMessageController,
                wallController,
                followController
        );

        actualSocialNetworker = ArgumentCaptor.forClass(AccountName.class);
        actualMessage = ArgumentCaptor.forClass(String.class);
    }

    @Test
    public void routesPostMessageCommands() {
        compositeConsoleRouter.route("Alice -> Hello!");

        verifyTheOnlyInteraction(postMessageController, c -> c.execute(actualSocialNetworker.capture(), actualMessage.capture()));
        assertThat(actualSocialNetworker.getValue(), is(alice));
        assertThat(actualMessage.getValue(), is("Hello!"));
    }

    @Test
    public void routesReadTimelineCommands() {
        compositeConsoleRouter.route("Alice");

        verifyTheOnlyInteraction(readTimelineController, c -> c.execute(actualSocialNetworker.capture()));
        assertThat(actualSocialNetworker.getValue(), is(alice));
    }

    @Test
    public void routesWallCommands() {
        compositeConsoleRouter.route("Alice wall");

        verifyTheOnlyInteraction(wallController, c -> c.execute(actualSocialNetworker.capture()));
        assertThat(actualSocialNetworker.getValue(), is(alice));
    }

    @Test
    public void routesFollowCommands() {
        ArgumentCaptor<AccountName> actualFollower = ArgumentCaptor.forClass(AccountName.class);
        ArgumentCaptor<AccountName> actualFollowee = ArgumentCaptor.forClass(AccountName.class);

        compositeConsoleRouter.route("Alice follows Bob");

        verifyTheOnlyInteraction(followController, c -> c.execute(actualFollower.capture(), actualFollowee.capture()));
        assertThat(actualFollower.getValue(), is(alice));
        assertThat(actualFollowee.getValue(), is(bob));
    }


    // Should have really used jMock to do strict mocking

    private <T> void verifyTheOnlyInteraction(T controller, Consumer<T> assertion) {
        assertion.accept(Mockito.verify(controller));
        allControllers.stream()
                .filter(c -> c != controller)
                .forEach(c -> Mockito.verifyZeroInteractions(c));
    }

    private <T> T addMock(Class<T> classToMock) {
        T mock = Mockito.mock(classToMock);
        allControllers.add(mock);
        return mock;
    }
}
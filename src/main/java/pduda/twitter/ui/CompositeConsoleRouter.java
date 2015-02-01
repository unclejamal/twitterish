package pduda.twitter.ui;

import pduda.twitter.ui.follow.FollowConsoleRouter;
import pduda.twitter.ui.follow.FollowController;
import pduda.twitter.ui.postmessage.PostMessageConsoleRouter;
import pduda.twitter.ui.postmessage.PostMessageController;
import pduda.twitter.ui.readtimeline.ReadTimelineConsoleRouter;
import pduda.twitter.ui.readtimeline.ReadTimelineController;
import pduda.twitter.ui.wall.WallConsoleRouter;
import pduda.twitter.ui.wall.WallController;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;

public class CompositeConsoleRouter {
    private final List<ConsoleRouter> routers;

    public CompositeConsoleRouter(ReadTimelineController readTimelineController,
                                  PostMessageController postMessageController,
                                  WallController wallController,
                                  FollowController followController) {
        routers = asList(
                new PostMessageConsoleRouter(postMessageController),
                new WallConsoleRouter(wallController),
                new FollowConsoleRouter(followController),
                new ReadTimelineConsoleRouter(readTimelineController)
        );
    }

    public void route(String command) {
        // TODO think about it
        Optional<ConsoleRouter> router = routers.stream()
                .filter(r -> r.canRoute(command))
                .findFirst();

        router.ifPresent(r -> r.route(command));
    }

}

package pduda.twitter.ui;

import java.util.List;

import static java.util.Arrays.asList;

public class CompositeConsoleRouter  {
    private final List<ConsoleRouter> routers;

    public CompositeConsoleRouter(ReadTimelineController readTimelineController,
                                  PostMessageController postMessageController,
                                  WallController wallController,
                                  FollowController followController) {
        routers = asList(
                new PostMessageConsoleRouter(postMessageController),
                new WallConsoleRouter(wallController),
                new ReadTimelineConsoleRouter(readTimelineController),
                new FollowConsoleRouter(followController)
        );
    }

    public void route(String command) {
        routers.stream()
                .filter(r -> r.canRoute(command))
                .forEach(r -> r.route(command));
    }

}

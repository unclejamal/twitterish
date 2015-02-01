package pduda.twitter.main;

import pduda.twitter.domain.SocialNetworkers;
import pduda.twitter.domain.usecase.FollowSocialNetworker;
import pduda.twitter.domain.usecase.PostMessage;
import pduda.twitter.domain.usecase.ReadTimeline;
import pduda.twitter.domain.usecase.Wall;
import pduda.twitter.persistence.InMemorySocialNetworkers;
import pduda.twitter.ui.*;
import pduda.twitter.ui.follow.FollowController;
import pduda.twitter.ui.postmessage.PostMessageController;
import pduda.twitter.ui.readtimeline.ReadTimelineController;
import pduda.twitter.ui.readtimeline.ReadTimelineView;
import pduda.twitter.ui.wall.WallController;
import pduda.twitter.ui.wall.WallView;

public class TwitterApplication implements Runnable {
    private final ConsoleInput input;
    private final ConsoleOutput output;
    private final CompositeConsoleRouter compositeConsoleRouter;

    public TwitterApplication(Clock clock, ConsoleOutput output, ConsoleInput input) {
        this.input = input;
        this.output = output;
        SocialNetworkers socialNetworkers = new InMemorySocialNetworkers();
        TimeElapsedView timeElapsedView = new TimeElapsedView(clock);

        this.compositeConsoleRouter = new CompositeConsoleRouter(
                new ReadTimelineController(
                        new ReadTimeline(socialNetworkers),
                        new ReadTimelineView(
                                output,
                                timeElapsedView
                        )
                ),
                new PostMessageController(
                        new PostMessage(
                                socialNetworkers,
                                clock
                        )
                ),
                new WallController(
                        new Wall(socialNetworkers),
                        new WallView(
                                output,
                                timeElapsedView
                        )
                ),
                new FollowController(
                        new FollowSocialNetworker(socialNetworkers)
                )
        );
    }

    @Override
    public void run() {
        while (true) {
            output.showPrompt();
            String command = input.getCommand();
            // TODO put into the router?
            if (command.equals("quit")) {
                break;
            }

            compositeConsoleRouter.route(command);
        }
    }

}

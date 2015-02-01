package pduda.twitter.main;

import pduda.twitter.domain.SocialNetworkers;
import pduda.twitter.domain.usecase.FollowSocialNetworker;
import pduda.twitter.domain.usecase.PostMessage;
import pduda.twitter.domain.usecase.ReadTimeline;
import pduda.twitter.domain.usecase.Wall;
import pduda.twitter.ui.Clock;
import pduda.twitter.ui.CompositeConsoleRouter;
import pduda.twitter.ui.ConsoleOutput;
import pduda.twitter.ui.TimeElapsedView;
import pduda.twitter.ui.follow.FollowController;
import pduda.twitter.ui.postmessage.PostMessageController;
import pduda.twitter.ui.readtimeline.ReadTimelineController;
import pduda.twitter.ui.readtimeline.ReadTimelineView;
import pduda.twitter.ui.wall.WallController;
import pduda.twitter.ui.wall.WallView;

import java.io.BufferedReader;
import java.io.IOException;

public class TwitterApplication implements Runnable {
    private final BufferedReader in;
    private final ConsoleOutput output;
    private CompositeConsoleRouter compositeConsoleRouter;

    public TwitterApplication(BufferedReader in, SocialNetworkers socialNetworkers, Clock clock, ConsoleOutput output) {
        this.in = in;
        this.output = output;
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
                new FollowController(new FollowSocialNetworker(socialNetworkers)));
    }

    @Override
    public void run() {
        while (true) {
            output.showPrompt();
            String command;
            try {
                command = in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // TODO put into the router?
            if (command.equals("quit")) {
                break;
            }

            compositeConsoleRouter.route(command);
        }
    }
}

package pduda.twitter.main;

import pduda.twitter.domain.Messages;
import pduda.twitter.domain.usecase.PostMessage;
import pduda.twitter.domain.usecase.ReadTimeline;
import pduda.twitter.domain.usecase.Wall;
import pduda.twitter.ui.*;

import java.io.BufferedReader;
import java.io.IOException;

public class TwitterApplication implements Runnable {
    private final BufferedReader in;
    private final ConsoleOutput output;
    private ConsoleRouter consoleRouter;

    public TwitterApplication(BufferedReader in, Messages messages, Clock clock, ConsoleOutput output) {
        this.in = in;
        this.output = output;
        TimeElapsedView timeElapsedView = new TimeElapsedView(clock);

        this.consoleRouter = new ConsoleRouter(
                new ReadTimelineController(
                        new ReadTimeline(messages),
                        new ReadTimelineView(
                                output,
                                timeElapsedView
                        )
                ),
                new PostMessageController(
                        new PostMessage(
                                messages,
                                clock
                        )
                ),
                new WallController(
                        new Wall(messages),
                        new WallView(
                                output,
                                timeElapsedView
                        )
                )
        );
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

            consoleRouter.routeCommand(command);
        }
    }
}

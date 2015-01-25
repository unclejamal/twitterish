package pduda.twitter.main;

import pduda.twitter.domain.Messages;
import pduda.twitter.ui.*;
import pduda.twitter.usecase.ReadTimeline;

import java.io.BufferedReader;
import java.io.IOException;

public class TwitterApplication implements Runnable {
    private final BufferedReader in;
    private final ConsoleOutput output;
    private final ReadTimelineController readTimelineController;

    public TwitterApplication(BufferedReader in, Messages messages, Clock clock, ConsoleOutput output) {
        this.in = in;
        this.output = output;
        this.readTimelineController = new ReadTimelineController(
                new ReadTimeline(messages),
                new ReadTimelineView(
                        output,
                        new TimeElapsedView(clock)
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
            if (command.equals("quit")) {
                break;
            }


            // TODO inject
            new ConsoleRouter(readTimelineController, null).execute(command);
        }
    }

}

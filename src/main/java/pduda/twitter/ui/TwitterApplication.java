package pduda.twitter.ui;

import pduda.twitter.infrastructure.InMemoryMessages;
import pduda.twitter.usecase.ReadTimeline;

import java.io.BufferedReader;
import java.io.IOException;

public class TwitterApplication implements Runnable {
    private final BufferedReader in;
    private final ConsoleOutput output;
    private TheController theController;

    public TwitterApplication(BufferedReader in, InMemoryMessages messages, Clock clock, ConsoleOutput output) {
        this.in = in;
        this.output = output;
        this.theController = new TheController(new ReadTimeline(messages), new ReadTimelineView(output, new TimeElapsedView(clock)));
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
            theController.commandEntered(command);
        }
    }

}

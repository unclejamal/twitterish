package pduda.twitter.ui;

import pduda.twitter.infrastructure.InMemoryMessages;
import pduda.twitter.usecase.ReadTimeline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class TwitterApplication implements Runnable {
    private final BufferedReader in;
    private final ConsoleOutput output;
    private TheController theController;

    public TwitterApplication(BufferedReader in, InMemoryMessages messages, Clock clock, ConsoleOutput output) {
        this.in = in;
        this.output = output;
        this.theController = new TheController(new ReadTimeline(messages), new TheView(output));
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        new Thread(new TwitterApplication(in, new InMemoryMessages(), new RealClock(), new ConsoleOutput(out))).start();
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

package pduda.twitter.ui;

import pduda.twitter.infrastructure.InMemoryMessages;
import pduda.twitter.usecase.ReadTimeline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class TwitterApplication implements Runnable {
    private final BufferedReader in;
    private final PrintWriter out;
    private TheController theController;

    public TwitterApplication(BufferedReader in, PrintWriter out, InMemoryMessages messages, Clock clock) {
        this.in = in;
        this.out = out;
        this.theController = new TheController(new ReadTimeline(messages), new TheView(new ConsoleOutput(out)));
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        new Thread(new TwitterApplication(in, out, new InMemoryMessages(), new RealClock())).start();
    }

    @Override
    public void run() {
        while (true) {
            out.print("> ");
            out.flush();
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

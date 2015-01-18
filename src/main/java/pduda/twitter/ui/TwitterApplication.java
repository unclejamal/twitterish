package pduda.twitter.ui;

import pduda.twitter.domain.SocialNetworker;
import pduda.twitter.domain.Timeline;
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

    public TwitterApplication(BufferedReader in, PrintWriter out, InMemoryMessages messages) {
        this.in = in;
        this.out = out;
        theController = new TheController(new ReadTimeline(messages), new TheView(out));
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        new Thread(new TwitterApplication(in, out, new InMemoryMessages())).start();
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

    private static class TheController {

        private ReadTimeline readTimeline;
        private TheView theView;

        public TheController(ReadTimeline readTimeline, TheView theView) {
            this.readTimeline = readTimeline;
            this.theView = theView;
        }

        public void commandEntered(String command) {
            Timeline timeline = readTimeline.execute(new SocialNetworker(command));

            theView.present(timeline);
        }
    }

    private static class TheView {
        private PrintWriter out;

        public TheView(PrintWriter out) {
            this.out = out;
        }

        public void present(Timeline timeline) {
            timeline.forEachMessage(message -> {
                out.printf("%s (%s ago)%n", message.getContent(), "5 minutes");
                out.flush();
            });
        }
    }

}

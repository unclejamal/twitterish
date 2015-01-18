package pduda.twitter.ui;

import pduda.twitter.domain.Message;
import pduda.twitter.domain.SocialNetworker;
import pduda.twitter.domain.Timeline;
import pduda.twitter.infrastructure.InMemoryMessages;
import pduda.twitter.usecase.ReadTimeline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;

public class TwitterApplication implements Runnable {
    private final BufferedReader in;
    private final PrintWriter out;
    private TheController theController;

    public TwitterApplication(BufferedReader in, PrintWriter out) {
        this.in = in;
        this.out = out;
        InMemoryMessages messages = new InMemoryMessages();
        messages.addMessage(new Message(new SocialNetworker("Alice"), "I love the weather today", new Date(1)));
        messages.addMessage(new Message(new SocialNetworker("Bob"), "Damn! We lost!", new Date(2)));
        messages.addMessage(new Message(new SocialNetworker("Bob"), "Good game though.", new Date(3)));
        theController = new TheController(new ReadTimeline(messages), new TheView(out));
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        new Thread(new TwitterApplication(in, out)).start();
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

package pduda.twitter.ui;

import pduda.twitter.domain.Timeline;

import java.time.Instant;

public class TheView {
    private ConsoleOutput output;

    public TheView(ConsoleOutput output) {
        this.output = output;
    }

    public void present(Timeline timeline) {
        timeline.forEachMessage(message -> {
            output.writeLineAndFlush(String.format("%s (%s ago)", message.getContent(), new TimeElapsedView().since(message.getPublicationDate())));
        });
    }

    public static class TimeElapsedView {
        public String since(Instant date) {
            return "5 minutes";
        }
    }
}

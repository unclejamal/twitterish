package pduda.twitter.ui;

import pduda.twitter.domain.Timeline;

import java.time.Instant;

public class TheView {
    private final TimeElapsedView timeElapsedView;
    private ConsoleOutput output;

    public TheView(ConsoleOutput output, TimeElapsedView timeElapsedView) {
        this.output = output;
        this.timeElapsedView = timeElapsedView;
    }

    public void present(Timeline timeline) {
        timeline.forEachMessage(message -> {
            output.writeLineAndFlush(String.format("%s (%s ago)", message.getContent(), timeElapsedView.since(message.getPublicationDate())));
        });
    }

    public static class TimeElapsedView {
        public String since(Instant date) {
            return "5 minutes";
        }
    }
}

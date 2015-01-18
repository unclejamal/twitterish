package pduda.twitter.ui;

import pduda.twitter.domain.Timeline;

public class TheView {
    private ConsoleOutput output;

    public TheView(ConsoleOutput output) {
        this.output = output;
    }

    public void present(Timeline timeline) {
        timeline.forEachMessage(message -> {
            output.writeLineAndFlush(String.format("%s (%s ago)", message.getContent(), "5 minutes"));
        });
    }
}

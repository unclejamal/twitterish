package pduda.twitter.ui.wall;

import pduda.twitter.domain.Timeline;
import pduda.twitter.ui.ConsoleOutput;
import pduda.twitter.ui.TimeElapsedView;

public class WallView {
    private final ConsoleOutput output;
    private final TimeElapsedView timeElapsedView;

    public WallView(ConsoleOutput output, TimeElapsedView timeElapsedView) {
        this.output = output;
        this.timeElapsedView = timeElapsedView;
    }

    public void present(Timeline timeline) {
        timeline.forEachMessage(message ->
                output.writeLineAndFlush(String.format(
                        "%s - %s (%s)",
                        // TODO train wrack?
                        message.getAccountName().getUsername(),
                        message.getContent(),
                        timeElapsedView.since(message.getPublicationDate())
                )));
    }
}

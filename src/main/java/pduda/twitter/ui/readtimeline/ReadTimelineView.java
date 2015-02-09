package pduda.twitter.ui.readtimeline;

import pduda.twitter.domain.Timeline;
import pduda.twitter.ui.ConsoleOutput;
import pduda.twitter.ui.TimeElapsedView;

public class ReadTimelineView {
    private final TimeElapsedView timeElapsedView;
    private final ConsoleOutput output;

    public ReadTimelineView(ConsoleOutput output, TimeElapsedView timeElapsedView) {
        this.output = output;
        this.timeElapsedView = timeElapsedView;
    }

    public void present(Timeline timeline) {
        timeline.forEachMessage(message -> output.writeLineAndFlush(
                String.format(
                        "%s (%s)",
                        message.getContent(),
                        timeElapsedView.since(message.getPublicationDate())
                )));
    }

}

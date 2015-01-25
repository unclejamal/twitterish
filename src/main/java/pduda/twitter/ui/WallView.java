package pduda.twitter.ui;

import pduda.twitter.domain.Timeline;

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
                        message.getSocialNetworker().getUsername(),
                        message.getContent(),
                        timeElapsedView.since(message.getPublicationDate())
                )));
    }
}

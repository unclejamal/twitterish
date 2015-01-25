package pduda.twitter.ui;

import pduda.twitter.domain.Timeline;

public class TheView {
    private final TimeElapsedView timeElapsedView;
    private ConsoleOutput output;

    public TheView(ConsoleOutput output, TimeElapsedView timeElapsedView) {
        this.output = output;
        this.timeElapsedView = timeElapsedView;
    }

    public void present(Timeline timeline) {
        timeline.forEachMessage(message -> {
            output.writeLineAndFlush(String.format("%s (%s)", message.getContent(),
//                    timeElapsedView.since(message.getPublicationDate())
                    "5 minutes ago"
                    ));
        });
    }

}

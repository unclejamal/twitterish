package pduda.twitter.ui;

import pduda.twitter.domain.SocialNetworker;
import pduda.twitter.domain.Timeline;
import pduda.twitter.usecase.ReadTimeline;

public class TheController {

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

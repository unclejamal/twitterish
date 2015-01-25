package pduda.twitter.ui;

import pduda.twitter.domain.SocialNetworker;
import pduda.twitter.domain.Timeline;
import pduda.twitter.usecase.ReadTimeline;

public class ReadTimelineController {

    private ReadTimeline readTimeline;
    private ReadTimelineView readTimelineView;

    public ReadTimelineController(ReadTimeline readTimeline, ReadTimelineView readTimelineView) {
        this.readTimeline = readTimeline;
        this.readTimelineView = readTimelineView;
    }

    public void commandEntered(String command) {
        Timeline timeline = readTimeline.execute(new SocialNetworker(command));
        readTimelineView.present(timeline);
    }
}
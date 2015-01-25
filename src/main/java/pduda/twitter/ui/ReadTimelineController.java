package pduda.twitter.ui;

import pduda.twitter.domain.SocialNetworker;
import pduda.twitter.domain.Timeline;
import pduda.twitter.domain.usecase.ReadTimeline;

public class ReadTimelineController {

    private ReadTimeline readTimeline;
    private ReadTimelineView readTimelineView;

    public ReadTimelineController(ReadTimeline readTimeline, ReadTimelineView readTimelineView) {
        this.readTimeline = readTimeline;
        this.readTimelineView = readTimelineView;
    }

    public void commandEntered(SocialNetworker socialNetworker) {
        Timeline timeline = readTimeline.execute(socialNetworker);
        readTimelineView.present(timeline);
    }
}

package pduda.twitter.ui.readtimeline;

import pduda.twitter.domain.AccountName;
import pduda.twitter.domain.Timeline;
import pduda.twitter.domain.usecase.ReadTimeline;

public class ReadTimelineController {

    private ReadTimeline readTimeline;
    private ReadTimelineView readTimelineView;

    public ReadTimelineController(ReadTimeline readTimeline, ReadTimelineView readTimelineView) {
        this.readTimeline = readTimeline;
        this.readTimelineView = readTimelineView;
    }

    public void execute(AccountName accountName) {
        Timeline timeline = readTimeline.execute(accountName);
        readTimelineView.present(timeline);
    }
}

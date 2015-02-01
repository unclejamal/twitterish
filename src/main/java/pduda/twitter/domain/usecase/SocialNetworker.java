package pduda.twitter.domain.usecase;

import pduda.twitter.domain.Message;
import pduda.twitter.domain.Timeline;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SocialNetworker {

    private final List<Message> messages;
    private final Set<SocialNetworker> followes;

    public SocialNetworker() {
        this.messages = new ArrayList<>();
        this.followes = new HashSet<>();
    }

    public Timeline getPersonalTimeline() {
        return Timeline.withReverseChronologicalOrder(messages);
    }

    public Timeline getWall() {
        return getPersonalTimeline().mergeWith(getTimelineForFollowees());
    }

    private Timeline getTimelineForFollowees() {
        return followes.stream()
                .map(f -> f.getPersonalTimeline())
                .reduce(new Timeline(), (t1, t2) -> t1.mergeWith(t2));
    }

    public void postMessage(Message message) {
        messages.add(message);
    }

    public void follow(SocialNetworker followee) {
        followes.add(followee);
    }
}

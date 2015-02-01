package pduda.twitter.domain.usecase;

import pduda.twitter.domain.Message;
import pduda.twitter.domain.Timeline;

import java.util.ArrayList;
import java.util.List;

public class SocialNetworker {

    private final List<Message> messages;

    public SocialNetworker() {
        this.messages = new ArrayList<>();
    }

    public Timeline getPersonalTimeline() {
        return Timeline.withReverseChronologicalOrder(messages);
    }

    public void postMessage(Message message) {
        messages.add(message);
    }
}

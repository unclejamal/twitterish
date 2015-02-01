package pduda.twitter.domain.usecase;

import pduda.twitter.domain.Message;
import pduda.twitter.domain.Timeline;

import java.util.List;

public class SocialNetworker {

    private final List<Message> messages;

    public SocialNetworker(List<Message> messages) {
        this.messages = messages;
    }

    public Timeline getPersonalTimeline() {
        return new Timeline(messages);
    }

}

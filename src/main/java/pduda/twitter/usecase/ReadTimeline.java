package pduda.twitter.usecase;

import pduda.twitter.infrastructure.InMemoryMessages;
import pduda.twitter.domain.SocialNetworker;
import pduda.twitter.domain.Timeline;

public class ReadTimeline {

    private InMemoryMessages messages;

    public ReadTimeline(InMemoryMessages messages) {
        this.messages = messages;
    }

    public Timeline execute(SocialNetworker socialNetworker) {
        return new Timeline(messages.getMessagesFor(socialNetworker));
    }
}

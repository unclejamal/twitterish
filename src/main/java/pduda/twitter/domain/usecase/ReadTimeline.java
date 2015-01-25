package pduda.twitter.domain.usecase;

import pduda.twitter.domain.Messages;
import pduda.twitter.domain.SocialNetworker;
import pduda.twitter.domain.Timeline;

public class ReadTimeline {

    private Messages messages;

    public ReadTimeline(Messages messages) {
        this.messages = messages;
    }

    public Timeline execute(SocialNetworker socialNetworker) {
        return new Timeline(messages.getMessagesFor(socialNetworker));
    }
}

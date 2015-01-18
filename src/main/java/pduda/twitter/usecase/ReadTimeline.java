package pduda.twitter.usecase;

import pduda.twitter.Messages;
import pduda.twitter.SocialNetworker;
import pduda.twitter.Timeline;

public class ReadTimeline {

    private Messages messages;

    public ReadTimeline(Messages messages) {
        this.messages = messages;
    }

    public Timeline execute(SocialNetworker socialNetworker) {
        return new Timeline(messages.getMessagesFor(socialNetworker));
    }
}

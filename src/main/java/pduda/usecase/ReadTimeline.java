package pduda.usecase;

import pduda.Messages;
import pduda.SocialNetworker;
import pduda.Timeline;

public class ReadTimeline {

    private Messages messages;

    public ReadTimeline(Messages messages) {
        this.messages = messages;
    }

    public Timeline execute(SocialNetworker socialNetworker) {
        return new Timeline(messages.getMessagesFor(socialNetworker));
    }
}

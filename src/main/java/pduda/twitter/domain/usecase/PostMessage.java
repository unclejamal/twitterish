package pduda.twitter.domain.usecase;

import pduda.twitter.domain.Message;
import pduda.twitter.domain.Messages;
import pduda.twitter.domain.SocialNetworker;
import pduda.twitter.ui.Clock;

public class PostMessage {
    private final Messages messages;
    private final Clock clock;

    public PostMessage(Messages messages, Clock clock) {
        this.messages = messages;
        this.clock = clock;
    }

    public void execute(SocialNetworker socialNetworker, String message) {
        this.messages.addMessage(new Message(socialNetworker, message, clock.instant()));
    }
}

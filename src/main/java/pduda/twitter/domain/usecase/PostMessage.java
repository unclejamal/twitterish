package pduda.twitter.domain.usecase;

import pduda.twitter.domain.AccountName;
import pduda.twitter.domain.Message;
import pduda.twitter.domain.Messages;
import pduda.twitter.ui.Clock;

public class PostMessage {
    private final Messages messages;
    private final Clock clock;

    public PostMessage(Messages messages, Clock clock) {
        this.messages = messages;
        this.clock = clock;
    }

    public void execute(AccountName accountName, String message) {
        this.messages.addMessage(new Message(accountName, message, clock.instant()));
    }
}

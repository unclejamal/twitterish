package pduda.twitter.domain.usecase;

import pduda.twitter.domain.AccountName;
import pduda.twitter.domain.Messages;
import pduda.twitter.domain.Timeline;

public class ReadTimeline {

    private Messages messages;

    public ReadTimeline(Messages messages) {
        this.messages = messages;
    }

    public Timeline execute(AccountName accountName) {
        return new Timeline(messages.getMessagesChronologicallyDescendingFor(accountName));
    }
}

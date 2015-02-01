package pduda.twitter.domain.usecase;

import pduda.twitter.domain.AccountName;
import pduda.twitter.domain.Messages;
import pduda.twitter.domain.Timeline;

public class Wall {
    private final Messages messages;

    public Wall(Messages messages) {
        this.messages = messages;
    }

    public Timeline execute(AccountName accountName) {
        return messages.getSocialNetworker(accountName).getPersonalTimeline();
    }
}

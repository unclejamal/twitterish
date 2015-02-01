package pduda.twitter.domain.usecase;

import pduda.twitter.domain.AccountName;
import pduda.twitter.domain.Message;
import pduda.twitter.domain.Messages;
import pduda.twitter.domain.Timeline;

import java.util.List;

public class ReadTimeline {

    private Messages messages;

    public ReadTimeline(Messages messages) {
        this.messages = messages;
    }

    public Timeline execute(AccountName accountName) {
        return new SocialNetworker(messages.getMessagesChronologicallyDescendingFor(accountName)).getPersonalTimeline();
    }

    private static class SocialNetworker {

        private final List<Message> messages;

        public SocialNetworker(List<Message> messages) {
            this.messages = messages;
        }

        public Timeline getPersonalTimeline() {
            return new Timeline(messages);
        }

    }
}

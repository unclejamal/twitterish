package pduda.twitter.persistence;

import pduda.twitter.domain.AccountName;
import pduda.twitter.domain.Message;
import pduda.twitter.domain.Messages;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class InMemoryMessages implements Messages {

    private final List<Message> messages;

    public InMemoryMessages() {
        messages = new ArrayList<>();
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public List<Message> getMessagesFor(AccountName accountName) {
        return messages.stream()
                .filter(m -> m.hasBeenPostedBy(accountName))
                .collect(toList());
    }
}

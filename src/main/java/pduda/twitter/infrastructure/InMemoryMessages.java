package pduda.twitter.infrastructure;

import pduda.twitter.domain.Message;
import pduda.twitter.domain.Messages;
import pduda.twitter.domain.SocialNetworker;

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

    public List<Message> getMessagesFor(SocialNetworker socialNetworker) {
        return messages.stream()
                .filter(m -> m.hasBeenPostedBy(socialNetworker))
                .collect(toList());
    }
}

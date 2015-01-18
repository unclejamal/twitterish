package pduda;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Messages {

    private final List<Message> messages;

    public Messages() {
        messages = new ArrayList<>();
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public List<Message> getMessagesFor(SocialNetworker socialNetworker) {
        return messages.stream()
                .filter(m -> m.isForSocialNetworker(socialNetworker))
                .collect(toList());
    }
}

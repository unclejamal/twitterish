package pduda.twitter.domain.usecase;

import pduda.twitter.domain.Message;
import pduda.twitter.domain.Timeline;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class SocialNetworker {

    private final List<Message> messages;

    public SocialNetworker() {
        this.messages = new ArrayList<>();
    }

    public Timeline getPersonalTimeline() {
        return new Timeline(
                messages.stream()
                        .sorted(comparing(Message::getPublicationDate).reversed())
                        .collect(Collectors.toList()
                        )
        );
    }

    public void postMessage(Message message) {
        messages.add(message);
    }
}

package pduda.twitter.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class Timeline {
    private List<Message> messages;

    public static Timeline withReverseChronologicalOrder(Collection<Message> messages) {
        return new Timeline(
                messages.stream()
                        .sorted(comparing(Message::getPublicationDate).reversed())
                        .collect(toList())
        );
    }

    public static Timeline empty() {
        return new Timeline(new ArrayList<>());
    }

    Timeline(List<Message> messages) {
        this.messages = messages;
    }

    public Timeline mergeWith(Timeline other) {
        ArrayList<Message> allMessages = new ArrayList<>();
        allMessages.addAll(messages);
        allMessages.addAll(other.messages);
        return withReverseChronologicalOrder(allMessages);
    }

    public void forEachMessage(Consumer<Message> block) {
        messages.forEach(block::accept);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Timeline timeline = (Timeline) o;

        if (messages != null ? !messages.equals(timeline.messages) : timeline.messages != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return messages != null ? messages.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Timeline{" +
                "messages=" + messages +
                '}';
    }
}

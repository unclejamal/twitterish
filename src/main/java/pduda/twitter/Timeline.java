package pduda.twitter;

import java.util.List;

public class Timeline {
    private List<Message> messages;

    public Timeline(List<Message> messages) {
        this.messages = messages;
    }

    public boolean hasMessages() {
        return false;
    }

    public boolean containsAll(List<Message> queriedMessages) {
        return this.messages.equals(queriedMessages);
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

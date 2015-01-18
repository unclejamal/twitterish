package pduda;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;

public class ReadingTimelineTest {

    private ReadTimeline readTimeline;
    private Messages messages = new Messages();

    @Before
    public void setUp() throws Exception {
        readTimeline = new ReadTimeline(messages);
    }

    @Test
    public void readingEmptyTimeline() {
        SocialNetworker alice = new SocialNetworker("Alice");
        Timeline timeline = readTimeline.execute(alice);
        assertThat(timeline, hasNoMessages());
    }

    @Test
    public void readingATimelineWithMultipleMessages() {
        SocialNetworker bob = new SocialNetworker("Bob");
        messages.addMessage(new Message(bob, "Damn! We lost!", new Date(1)));
        messages.addMessage(new Message(bob, "Good game though.", new Date(2)));
        Timeline timeline = readTimeline.execute(bob);
        assertThat(timeline, hasMessages(new Message(bob, "Damn! We lost!", new Date(1)), new Message(bob, "Good game though.", new Date(2))));
    }

    private Matcher<? super Timeline> hasMessages(final Message... messages) {
        return new TypeSafeMatcher<Timeline>() {
            @Override
            protected boolean matchesSafely(Timeline timeline) {
                return timeline.containsAll(Arrays.asList(messages));
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("the timeline to have messages: ");
                for (Message message : messages) {
                    description.appendValue(message);
                    description.appendText(", ");
                }
            }
        };
    }

    private Matcher<Timeline> hasNoMessages() {
        return new TypeSafeMatcher<Timeline>() {
            @Override
            protected boolean matchesSafely(Timeline timeline) {
                return !timeline.hasMessages();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("the timeline to have no messages");
            }
        };
    }

    private static class ReadTimeline {

        private Messages messages;

        public ReadTimeline(Messages messages) {
            this.messages = messages;
        }

        public Timeline execute(SocialNetworker socialNetworker) {
            return new Timeline(messages.getMessagesFor(socialNetworker));
        }
    }

    private static class Timeline {
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

    private static class SocialNetworker {
        private String username;

        public SocialNetworker(String username) {
            this.username = username;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            SocialNetworker that = (SocialNetworker) o;

            if (username != null ? !username.equals(that.username) : that.username != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return username != null ? username.hashCode() : 0;
        }

        @Override
        public String toString() {
            return "SocialNetworker{" +
                    "username='" + username + '\'' +
                    '}';
        }
    }

    private static class Messages {

        private final List<Message> messages;

        public Messages() {
            messages = new ArrayList<>();
        }

        public void addMessage(Message message) {
            messages.add(message);
        }

        public List<Message> getMessagesFor(SocialNetworker socialNetworker) {
            return messages.stream()
                    .filter(m -> socialNetworker.equals(m.socialNetworker))
                    .collect(toList());
        }
    }

    private static class Message {
        private final SocialNetworker socialNetworker;
        private final String content;
        private final Date date;

        public Message(SocialNetworker socialNetworker, String content, Date date) {
            this.socialNetworker = socialNetworker;
            this.content = content;
            this.date = date;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Message message = (Message) o;

            if (content != null ? !content.equals(message.content) : message.content != null) return false;
            if (date != null ? !date.equals(message.date) : message.date != null) return false;
            if (socialNetworker != null ? !socialNetworker.equals(message.socialNetworker) : message.socialNetworker != null)
                return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = socialNetworker != null ? socialNetworker.hashCode() : 0;
            result = 31 * result + (content != null ? content.hashCode() : 0);
            result = 31 * result + (date != null ? date.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Message{" +
                    "socialNetworker=" + socialNetworker +
                    ", content='" + content + '\'' +
                    ", date=" + date +
                    '}';
        }
    }
}

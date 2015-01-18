package pduda.twitter.domain;

import java.util.Date;

public class Message {
    private final SocialNetworker socialNetworker;
    private final String content;
    private final Date date;

    public Message(SocialNetworker socialNetworker, String content, Date date) {
        this.socialNetworker = socialNetworker;
        this.content = content;
        this.date = date;
    }

    public boolean hasBeenPostedBy(SocialNetworker socialNetworker) {
        return socialNetworker.equals(this.socialNetworker);
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

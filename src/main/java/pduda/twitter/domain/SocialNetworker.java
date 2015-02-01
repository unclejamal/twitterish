package pduda.twitter.domain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SocialNetworker {

    private final AccountName accountName;
    private final List<Message> messages;
    private final Set<SocialNetworker> followes;

    public SocialNetworker(AccountName accountName) {
        this.accountName = accountName;
        this.messages = new ArrayList<>();
        this.followes = new HashSet<>();
    }

    public Timeline getPersonalTimeline() {
        return Timeline.withReverseChronologicalOrder(messages);
    }

    public Timeline getWall() {
        return getPersonalTimeline().mergeWith(getTimelineForFollowees());
    }

    private Timeline getTimelineForFollowees() {
        return followes.stream()
                .map(f -> f.getPersonalTimeline())
                .reduce(new Timeline(), (t1, t2) -> t1.mergeWith(t2));
    }

    public void postMessageWithContent(String messageContent, Instant messageTime) {
        messages.add(new Message(accountName, messageContent, messageTime));
    }

    public void follow(SocialNetworker followee) {
        followes.add(followee);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SocialNetworker that = (SocialNetworker) o;

        if (accountName != null ? !accountName.equals(that.accountName) : that.accountName != null) return false;
        if (followes != null ? !followes.equals(that.followes) : that.followes != null) return false;
        if (messages != null ? !messages.equals(that.messages) : that.messages != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = accountName != null ? accountName.hashCode() : 0;
        result = 31 * result + (messages != null ? messages.hashCode() : 0);
        result = 31 * result + (followes != null ? followes.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SocialNetworker{" +
                "accountName=" + accountName +
                ", messages=" + messages +
                ", followes=" + followes +
                '}';
    }
}

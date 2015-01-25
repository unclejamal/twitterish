package pduda.twitter.domain;

import java.util.List;

public interface Messages {
    List<Message> getMessagesFor(SocialNetworker socialNetworker);
}

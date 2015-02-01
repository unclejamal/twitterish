package pduda.twitter.domain;

import pduda.twitter.domain.usecase.SocialNetworker;

import java.util.List;

public interface Messages {
    List<Message> getMessagesChronologicallyDescendingFor(AccountName accountName);

    SocialNetworker getSocialNetworker(AccountName accountName);

    void addMessage(Message message);
}

package pduda.twitter.domain;

import java.util.List;

public interface Messages {
    List<Message> getMessagesFor(AccountName accountName);

    void addMessage(Message message);
}

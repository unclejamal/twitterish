package pduda.twitter.domain;

import java.util.List;

public interface Messages {
    List<Message> getMessagesChronologicallyDescendingFor(AccountName accountName);

    void addMessage(Message message);
}

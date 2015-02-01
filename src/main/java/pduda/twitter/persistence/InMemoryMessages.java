package pduda.twitter.persistence;

import pduda.twitter.domain.AccountName;
import pduda.twitter.domain.Message;
import pduda.twitter.domain.Messages;
import pduda.twitter.domain.usecase.SocialNetworker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class InMemoryMessages implements Messages {

    private final List<Message> messages;
    private final Map<AccountName, SocialNetworker> socialNetworkers;

    public InMemoryMessages() {
        messages = new ArrayList<>();
        socialNetworkers = new HashMap<>();
    }

    @Override
    public SocialNetworker getSocialNetworker(AccountName accountName) {
        return socialNetworkers.get(accountName);
    }

    @Override
    public void addMessage(Message message) {
        if (!socialNetworkers.containsKey(message.getAccountName())) {
            socialNetworkers.put(message.getAccountName(), new SocialNetworker(new ArrayList<>(asList(message))));
        } else {
            socialNetworkers.get(message.getAccountName()).postMessage(message);
        }
    }

    @Override
    public List<Message> getMessagesChronologicallyDescendingFor(AccountName accountName) {
        return messages.stream()
                .filter(m -> m.hasBeenPostedBy(accountName))
                .sorted(comparing(Message::getPublicationDate).reversed())
                .collect(toList());
    }
}

package pduda.twitter.persistence;

import pduda.twitter.domain.AccountName;
import pduda.twitter.domain.Message;
import pduda.twitter.domain.Messages;
import pduda.twitter.domain.usecase.SocialNetworker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.asList;

public class InMemoryMessages implements Messages {

    private final Map<AccountName, SocialNetworker> socialNetworkers;

    public InMemoryMessages() {
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
    public SocialNetworker getOrCreateSocialNetworker(AccountName accountName) {
        if (!socialNetworkers.containsKey(accountName)) {
            socialNetworkers.put(accountName, new SocialNetworker(new ArrayList<>(asList())));
        }

        return socialNetworkers.get(accountName);
    }
}

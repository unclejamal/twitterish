package pduda.twitter.persistence;

import pduda.twitter.domain.AccountName;
import pduda.twitter.domain.SocialNetworker;
import pduda.twitter.domain.SocialNetworkers;

import java.util.HashMap;
import java.util.Map;

public class InMemorySocialNetworkers implements SocialNetworkers {

    private final Map<AccountName, SocialNetworker> socialNetworkers;

    public InMemorySocialNetworkers() {
        socialNetworkers = new HashMap<>();
    }

    @Override
    public SocialNetworker getOrCreateSocialNetworker(AccountName accountName) {
        if (!socialNetworkers.containsKey(accountName)) {
            socialNetworkers.put(accountName, new SocialNetworker(accountName));
        }

        return getSocialNetworker(accountName);
    }

    @Override
    public SocialNetworker getSocialNetworker(AccountName accountName) {
        return socialNetworkers.get(accountName);
    }
}

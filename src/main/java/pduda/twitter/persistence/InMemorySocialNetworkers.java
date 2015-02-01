package pduda.twitter.persistence;

import pduda.twitter.domain.AccountName;
import pduda.twitter.domain.SocialNetworkers;
import pduda.twitter.domain.usecase.SocialNetworker;

import java.util.HashMap;
import java.util.Map;

public class InMemorySocialNetworkers implements SocialNetworkers {

    private final Map<AccountName, SocialNetworker> socialNetworkers;

    public InMemorySocialNetworkers() {
        socialNetworkers = new HashMap<>();
    }

    @Override
    public SocialNetworker getSocialNetworker(AccountName accountName) {
        return socialNetworkers.get(accountName);
    }

    @Override
    public SocialNetworker getOrCreateSocialNetworker(AccountName accountName) {
        if (!socialNetworkers.containsKey(accountName)) {
            socialNetworkers.put(accountName, new SocialNetworker());
        }

        return socialNetworkers.get(accountName);
    }
}

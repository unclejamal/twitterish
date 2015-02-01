package pduda.twitter.domain.usecase;

import pduda.twitter.domain.AccountName;
import pduda.twitter.domain.SocialNetworkers;

public class FollowSocialNetworker {
    private final SocialNetworkers socialNetworkers;

    public FollowSocialNetworker(SocialNetworkers socialNetworkers) {
        this.socialNetworkers = socialNetworkers;
    }

    public void execute(AccountName follower, AccountName followee) {
        socialNetworkers.getSocialNetworker(follower).follow(socialNetworkers.getSocialNetworker(followee));

    }
}

package pduda.twitter.ui.follow;

import pduda.twitter.domain.AccountName;
import pduda.twitter.domain.usecase.FollowSocialNetworker;

public class FollowController {
    private final FollowSocialNetworker followSocialNetworker;

    public FollowController(FollowSocialNetworker followSocialNetworker) {
        this.followSocialNetworker = followSocialNetworker;
    }

    public void execute(AccountName follower, AccountName followee) {
        followSocialNetworker.execute(follower, followee);
    }
}

package pduda.twitter.ui.follow;

import pduda.twitter.domain.AccountName;
import pduda.twitter.domain.usecase.FollowSocialNetworker;

public class FollowController {
    public void execute(AccountName follower, AccountName followee) {
        FollowSocialNetworker followSocialNetworker = new FollowSocialNetworker();
        followSocialNetworker.execute(follower, followee);
    }
}

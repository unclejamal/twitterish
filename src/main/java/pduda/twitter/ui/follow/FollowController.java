package pduda.twitter.ui.follow;

import pduda.twitter.domain.SocialNetworker;
import pduda.twitter.domain.usecase.FollowSocialNetworker;

public class FollowController {
    public void execute(SocialNetworker follower, SocialNetworker followee) {
        FollowSocialNetworker followSocialNetworker = new FollowSocialNetworker();
        followSocialNetworker.execute(follower, followee);
    }
}

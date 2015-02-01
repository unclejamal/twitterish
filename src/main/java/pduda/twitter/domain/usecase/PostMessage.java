package pduda.twitter.domain.usecase;

import pduda.twitter.domain.AccountName;
import pduda.twitter.domain.SocialNetworkers;
import pduda.twitter.ui.Clock;

public class PostMessage {
    private final SocialNetworkers socialNetworkers;
    private final Clock clock;

    public PostMessage(SocialNetworkers socialNetworkers, Clock clock) {
        this.socialNetworkers = socialNetworkers;
        this.clock = clock;
    }

    public void execute(AccountName accountName, String content) {
        SocialNetworker socialNetworker = this.socialNetworkers.getOrCreateSocialNetworker(accountName);
        socialNetworker.postMessageWithContent(content, clock.instant());
    }
}

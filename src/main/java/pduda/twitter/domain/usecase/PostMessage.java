package pduda.twitter.domain.usecase;

import pduda.twitter.domain.AccountName;
import pduda.twitter.domain.Message;
import pduda.twitter.domain.SocialNetworkers;
import pduda.twitter.ui.Clock;

public class PostMessage {
    private final SocialNetworkers socialNetworkers;
    private final Clock clock;

    public PostMessage(SocialNetworkers socialNetworkers, Clock clock) {
        this.socialNetworkers = socialNetworkers;
        this.clock = clock;
    }

    public void execute(AccountName accountName, String message) {
        this.socialNetworkers.getOrCreateSocialNetworker(accountName).postMessage(new Message(accountName, message, clock.instant()));
    }
}

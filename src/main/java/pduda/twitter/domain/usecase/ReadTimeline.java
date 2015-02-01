package pduda.twitter.domain.usecase;

import pduda.twitter.domain.AccountName;
import pduda.twitter.domain.SocialNetworkers;
import pduda.twitter.domain.Timeline;

public class ReadTimeline {

    private SocialNetworkers socialNetworkers;

    public ReadTimeline(SocialNetworkers socialNetworkers) {
        this.socialNetworkers = socialNetworkers;
    }

    public Timeline execute(AccountName accountName) {
        return socialNetworkers.getSocialNetworker(accountName).getPersonalTimeline();
    }

}

package pduda.twitter.domain;

import pduda.twitter.domain.usecase.SocialNetworker;

public interface SocialNetworkers {

    SocialNetworker getSocialNetworker(AccountName accountName);

    SocialNetworker getOrCreateSocialNetworker(AccountName accountName);
}

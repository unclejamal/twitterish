package pduda.twitter.domain;

import pduda.twitter.domain.usecase.SocialNetworker;

public interface Messages {

    SocialNetworker getSocialNetworker(AccountName accountName);

    void addMessage(Message message);

    SocialNetworker getOrCreateSocialNetworker(AccountName accountName);
}

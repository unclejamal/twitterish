package pduda.twitter.domain;

public interface SocialNetworkers {

    SocialNetworker getSocialNetworker(AccountName accountName);

    SocialNetworker getOrCreateSocialNetworker(AccountName accountName);
}

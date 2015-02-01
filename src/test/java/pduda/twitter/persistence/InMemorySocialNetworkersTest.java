package pduda.twitter.persistence;

import org.junit.Before;
import org.junit.Test;
import pduda.twitter.domain.AccountName;
import pduda.twitter.domain.usecase.SocialNetworker;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class InMemorySocialNetworkersTest {

    private AccountName newAccountName;
    private InMemorySocialNetworkers inMemorySocialNetworkers;

    @Before
    public void setUp() throws Exception {
        newAccountName = new AccountName("bob");
        inMemorySocialNetworkers = new InMemorySocialNetworkers();
    }

    @Test
    public void createsASocialNetworkerWhenNotPresent() {
        SocialNetworker createdSocialNetworker = inMemorySocialNetworkers.getOrCreateSocialNetworker(newAccountName);
        assertThat(createdSocialNetworker, is(new SocialNetworker(newAccountName)));
    }

    @Test
    public void returnsExistingSocialNetworkersInsteadOfCreatingThem() {
        SocialNetworker createdSocialNetworker = inMemorySocialNetworkers.getOrCreateSocialNetworker(newAccountName);
        SocialNetworker retrievedSocialNetworker = inMemorySocialNetworkers.getOrCreateSocialNetworker(newAccountName);
        assertThat(createdSocialNetworker, is(retrievedSocialNetworker));
    }

    @Test
    public void returnsExistingSocialNetworkers() {
        SocialNetworker createdSocialNetworker = inMemorySocialNetworkers.getOrCreateSocialNetworker(newAccountName);
        SocialNetworker retrievedSocialNetworker = inMemorySocialNetworkers.getSocialNetworker(newAccountName);
        assertThat(createdSocialNetworker, is(retrievedSocialNetworker));
    }

}
package pduda.twitter.persistence;

import org.junit.Before;
import pduda.twitter.domain.AccountName;

public class InMemoryMessagesTest {

    private InMemorySocialNetworkers messages;
    private AccountName bob;

    @Before
    public void setUp() throws Exception {
        messages = new InMemorySocialNetworkers();
        bob = new AccountName("bob");
    }
}
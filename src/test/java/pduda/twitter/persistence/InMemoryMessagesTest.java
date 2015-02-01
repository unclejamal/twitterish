package pduda.twitter.persistence;

import org.junit.Before;
import pduda.twitter.domain.AccountName;

public class InMemoryMessagesTest {

    private InMemoryMessages messages;
    private AccountName bob;

    @Before
    public void setUp() throws Exception {
        messages = new InMemoryMessages();
        bob = new AccountName("bob");
    }
}
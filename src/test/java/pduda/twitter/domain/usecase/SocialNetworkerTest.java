package pduda.twitter.domain.usecase;

import org.junit.Before;
import org.junit.Test;
import pduda.twitter.domain.AccountName;
import pduda.twitter.domain.Message;
import pduda.twitter.domain.Timeline;

import java.time.Instant;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static pduda.twitter.util.ObjectMother.somePublicationDate;

public class SocialNetworkerTest {

    private SocialNetworker bob;

    @Before
    public void setUp() {
        bob = new SocialNetworker(new AccountName("alice"));
    }

    @Test
    public void hasAnEmptyPersonalTimelineWhenNoMessagesPosted() {
        assertThat(bob.getPersonalTimeline(), is(new Timeline(emptyList())));
    }

    @Test
    public void hasAnEmptyWall() {
        assertThat(bob.getWall(), is(new Timeline(emptyList())));
    }

    @Test
    public void hasAWall() {
        SocialNetworker alice = new SocialNetworker(new AccountName("alice"));
        Message alicesMessage = new Message(new AccountName("alice"), "content1", somePublicationDate());
        alice.postMessage(alicesMessage);
        Message bobsMessage = new Message(new AccountName("bob"), "content2", somePublicationDate());
        bob.postMessage(bobsMessage);

        bob.follow(alice);

        assertThat(bob.getWall(), is(Timeline.withReverseChronologicalOrder(asList(bobsMessage, alicesMessage))));
    }

    @Test
    public void hasPersonalTimelineAfterMessagesPosted() {
        Instant publicationDate = somePublicationDate();
        Message soonerMessage = new Message(new AccountName("bob"), "content1", publicationDate);
        Message laterMessage = new Message(new AccountName("bob"), "content2", publicationDate.plusMillis(1000));

        bob.postMessage(soonerMessage);
        bob.postMessage(laterMessage);

        assertThat(bob.getPersonalTimeline(), is(Timeline.withReverseChronologicalOrder(asList(soonerMessage, laterMessage))));
    }

}

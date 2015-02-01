package pduda.twitter.domain.usecase;

import org.junit.Before;
import org.junit.Test;
import pduda.twitter.domain.AccountName;
import pduda.twitter.domain.Message;
import pduda.twitter.domain.SocialNetworker;
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
        bob = new SocialNetworker(new AccountName("bob"));
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
        Instant alicesMessageTime = somePublicationDate();
        alice.postMessageWithContent("alices content", alicesMessageTime);
        Instant bobsMessageTime = somePublicationDate();
        bob.postMessageWithContent("bobs content", bobsMessageTime);

        bob.follow(alice);

        assertThat(bob.getWall(), is(Timeline.withReverseChronologicalOrder(asList(
                new Message(new AccountName("bob"), "bobs content", alicesMessageTime),
                new Message(new AccountName("alice"), "alices content", bobsMessageTime)
        ))));
    }

    @Test
    public void hasPersonalTimelineAfterMessagesPosted() {
        Instant publicationDate = somePublicationDate();

        bob.postMessageWithContent("content1", publicationDate);
        bob.postMessageWithContent("content2", publicationDate.plusMillis(1000));

        assertThat(bob.getPersonalTimeline(), is(Timeline.withReverseChronologicalOrder(asList(
                new Message(new AccountName("bob"), "content1", publicationDate),
                new Message(new AccountName("bob"), "content2", publicationDate.plusMillis(1000))
        ))));
    }

}

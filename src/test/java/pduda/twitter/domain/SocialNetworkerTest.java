package pduda.twitter.domain;

import org.junit.Before;
import org.junit.Test;

import java.time.Instant;

import static java.util.Arrays.asList;
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
        assertThat(bob.getPersonalTimeline(), is(Timeline.empty()));
    }

    @Test
    public void hasAnEmptyWall() {
        assertThat(bob.getWall(), is(Timeline.empty()));
    }

    @Test
    public void hasAWallWithMessagesPostedPersonallyAndByFollowees() {
        SocialNetworker alice = new SocialNetworker(new AccountName("alice"));
        Instant alicesMessageTime = somePublicationDate();
        alice.postMessageWithContent("alices content", alicesMessageTime);
        Instant bobsMessageTime = alicesMessageTime.plusSeconds(1);
        bob.postMessageWithContent("bobs content", bobsMessageTime);

        bob.follow(alice);

        assertThat(bob.getWall(), is(Timeline.withReverseChronologicalOrder(asList(
                new Message(new AccountName("bob"), "bobs content", bobsMessageTime),
                new Message(new AccountName("alice"), "alices content", alicesMessageTime)
        ))));
    }

    @Test
    public void hasPersonalTimelineAfterMessagesPosted() {
        bob.postMessageWithContent("content1", somePublicationDate());
        bob.postMessageWithContent("content2", somePublicationDate().plusSeconds(1));

        assertThat(bob.getPersonalTimeline(), is(Timeline.withReverseChronologicalOrder(asList(
                new Message(new AccountName("bob"), "content1", somePublicationDate()),
                new Message(new AccountName("bob"), "content2", somePublicationDate().plusSeconds(1))
        ))));
    }

}

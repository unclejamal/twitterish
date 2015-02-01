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

    private SocialNetworker socialNetworker;

    @Before
    public void setUp() {
        socialNetworker = new SocialNetworker();
    }

    @Test
    public void hasAnEmptyPersonalTimelineWhenNoMessagesPosted() {
        assertThat(socialNetworker.getPersonalTimeline(), is(new Timeline(emptyList())));
    }

    @Test
    public void hasPersonalTimelineAfterMessagesPosted() {
        Instant publicationDate = somePublicationDate();
        Message soonerMessage = new Message(new AccountName("bob"), "content1", publicationDate);
        Message laterMessage = new Message(new AccountName("bob"), "content2", publicationDate.plusMillis(1000));

        socialNetworker.postMessage(soonerMessage);
        socialNetworker.postMessage(laterMessage);

        assertThat(socialNetworker.getPersonalTimeline(), is(Timeline.withReverseChronologicalOrder(asList(soonerMessage, laterMessage))));
    }

}

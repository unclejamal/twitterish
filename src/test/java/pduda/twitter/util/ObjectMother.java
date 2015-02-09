package pduda.twitter.util;

import pduda.twitter.domain.AccountName;
import pduda.twitter.domain.Message;
import pduda.twitter.domain.Timeline;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneOffset;

import static java.time.Month.JANUARY;
import static java.util.Arrays.asList;

public class ObjectMother {
    public static Instant somePublicationDate() {
        return someDay().atTime(20 ,0).toInstant(ZoneOffset.UTC);
    }

    public static LocalDate someDay() {
        return Year.of(2015).atMonth(JANUARY).atDay(30);
    }

    public static AccountName someAccountName() {
        return new AccountName("bob");
    }

    public static Timeline nonEmptyTimeline() {
        return Timeline.withReverseChronologicalOrder(asList(someMessage()));
    }

    public static Message someMessage() {
        return new Message(someAccountName(), "some content", somePublicationDate());
    }
}

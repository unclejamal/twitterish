package pduda.twitter.util;

import pduda.twitter.domain.AccountName;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneOffset;

import static java.time.Month.JANUARY;

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
}

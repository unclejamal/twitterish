package pduda.twitter.util;

import pduda.twitter.domain.AccountName;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Year;

import static java.time.Month.JANUARY;

public class ObjectMother {
    public static Instant somePublicationDate() {
        return Instant.now();
    }

    public static LocalDate someDay() {
        return Year.of(2015).atMonth(JANUARY).atDay(30);
    }

    public static AccountName someAccountName() {
        return new AccountName("bob");
    }
}

package pduda.twitter.ui;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class TimeElapsedView {
    private final Clock clock;

    public TimeElapsedView(Clock clock) {
        this.clock = clock;
    }

    public String since(Instant date) {
        Instant instant = clock.instant();
        Duration elapsedTime = Duration.between(date, instant);
        long elapsedSeconds = elapsedTime.get(ChronoUnit.SECONDS);

        if (elapsedSeconds >= 60) {
            return presentAsMinutes(elapsedTime);
        }

        return presentAsSeconds(elapsedSeconds);
    }

    private String presentAsSeconds(long elapsedSeconds) {
        return String.format("%s %s ago", elapsedSeconds, pluraliseSeconds(elapsedSeconds));
    }

    private String presentAsMinutes(Duration duration) {
        long elapsedMinutes = duration.toMinutes();
        return String.format("%s %s ago", elapsedMinutes, pluraliseMinutes(elapsedMinutes));
    }

    private String pluraliseMinutes(long elapsedMinutes) {
        return elapsedMinutes > 1 ? "minutes" : "minute";
    }

    private String pluraliseSeconds(long elapsedSeconds) {
        return elapsedSeconds > 1 ? "seconds" : "second";
    }
}

package pduda.twitter.ui;

import java.time.Instant;

public class TimeElapsedView {
    private final Clock clock;

    public TimeElapsedView(Clock clock) {
        this.clock = clock;
    }

    public String since(Instant date) {
        return "1 second ago";
    }
}

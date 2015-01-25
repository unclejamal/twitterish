package pduda.twitter.util;

import pduda.twitter.ui.Clock;

import java.time.Instant;

public class FixedClock implements Clock {
    private Instant instant;

    public void fixAt(Instant dateTime) {
        this.instant = dateTime;
    }

    @Override
    public Instant instant() {
        return instant;
    }
}

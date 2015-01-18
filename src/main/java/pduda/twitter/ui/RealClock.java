package pduda.twitter.ui;

import java.time.Instant;

public class RealClock implements Clock {
    @Override
    public Instant instant() {
        return Instant.now();
    }
}

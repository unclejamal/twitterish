package pduda.twitter.journey;

import org.junit.Before;
import org.junit.Test;
import pduda.twitter.main.TwitterApplication;
import pduda.twitter.persistence.InMemorySocialNetworkers;
import pduda.twitter.ui.ConsoleOutput;
import pduda.twitter.util.FixedClock;

import java.io.*;
import java.time.LocalDateTime;

import static java.lang.System.lineSeparator;
import static java.time.ZoneOffset.UTC;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static pduda.twitter.util.ObjectMother.someDay;

public class PostingMessagesToTimelineUiTest {
    public static final String PROMPT = "> ";
    private PrintWriter inWriter;
    private BufferedReader outReader;
    private FixedClock clock;

    @Before
    public void startApplication() throws Exception {
        PipedOutputStream inStream = new PipedOutputStream();
        inWriter = new PrintWriter(inStream, true);
        PipedInputStream outStream = new PipedInputStream();
        outReader = new BufferedReader(new InputStreamReader(outStream));

        BufferedReader in = new BufferedReader(new InputStreamReader(new PipedInputStream(inStream)));
        PrintWriter out = new PrintWriter(new PipedOutputStream(outStream), true);

        clock = new FixedClock();
        new Thread(new TwitterApplication(in, new InMemorySocialNetworkers(), clock, new ConsoleOutput(out))).start();
    }

    @Test(timeout = 1000)
    public void postMessagesToTheTimeline() throws Exception {
        enter("Alice -> I love the weather today", whenTimeIs(someDay().atTime(9, 55)));
        enter("Bob -> Damn! We lost!", whenTimeIs(someDay().atTime(9, 58)));
        enter("Bob -> Good game though.", whenTimeIs(someDay().atTime(9, 59)));

        enter("Alice", whenTimeIs(someDay().atTime(10, 0)));
        assertOutputLines(
                "I love the weather today (5 minutes ago)"
        );

        enter("Bob");
        assertOutputLines(
                "Good game though. (1 minute ago)",
                "Damn! We lost! (2 minutes ago)"
        );

        enter("quit");
    }

    private Runnable whenTimeIs(LocalDateTime localDateTime) {
        return () -> clock.fixAt(localDateTime.toInstant(UTC));
    }

    private void enter(String command, Runnable context) throws IOException {
        read(PROMPT);
        context.run();
        write(command);
    }

    private void enter(String command) throws IOException {
        enter(command, () -> {});
    }

    private void read(String expectedOutput) throws IOException {
        int length = expectedOutput.length();
        char[] buffer = new char[length];
        outReader.read(buffer, 0, length);
        assertThat(String.valueOf(buffer), is(expectedOutput));
    }

    private void assertOutputLines(String... expectedOutput) throws IOException {
        for (String line : expectedOutput) {
            read(line + lineSeparator());
        }
    }

    private void write(String input) {
        inWriter.println(input);
    }
}

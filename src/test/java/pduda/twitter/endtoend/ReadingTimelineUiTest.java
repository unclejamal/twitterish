package pduda.twitter.endtoend;

import org.junit.Before;
import org.junit.Test;
import pduda.twitter.domain.AccountName;
import pduda.twitter.main.TwitterApplication;
import pduda.twitter.persistence.InMemorySocialNetworkers;
import pduda.twitter.ui.ConsoleOutput;
import pduda.twitter.util.FixedClock;

import java.io.*;

import static java.lang.System.lineSeparator;
import static java.time.ZoneOffset.UTC;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static pduda.twitter.util.ObjectMother.someDay;

// TODO probably can be removed since PostingMessagesToTimelineUiTest does the job
public class ReadingTimelineUiTest {
    public static final String PROMPT = "> ";
    public static final AccountName alice = new AccountName("Alice");
    public static final AccountName bob = new AccountName("Bob");
    private PrintWriter inWriter;
    private BufferedReader outReader;
    private InMemorySocialNetworkers messages;
    private FixedClock clock;

    @Before
    public void startApplication() throws Exception {
        PipedOutputStream inStream = new PipedOutputStream();
        inWriter = new PrintWriter(inStream, true);
        PipedInputStream outStream = new PipedInputStream();
        outReader = new BufferedReader(new InputStreamReader(outStream));

        BufferedReader in = new BufferedReader(new InputStreamReader(new PipedInputStream(inStream)));
        PrintWriter out = new PrintWriter(new PipedOutputStream(outStream), true);

        messages = new InMemorySocialNetworkers();
        clock = new FixedClock();
        new Thread(new TwitterApplication(in, messages, clock, new ConsoleOutput(out))).start();
    }

    @Test(timeout = 1000)
    public void readTimeline() throws IOException {
        clock.fixAt(someDay().atTime(10, 0).toInstant(UTC));

        messages.getOrCreateSocialNetworker(alice).postMessageWithContent("I love the weather today", someDay().atTime(9, 55).toInstant(UTC));
        messages.getOrCreateSocialNetworker(bob).postMessageWithContent("Damn! We lost!", someDay().atTime(9, 58).toInstant(UTC));
        messages.getOrCreateSocialNetworker(bob).postMessageWithContent("Good game though.", someDay().atTime(9, 59).toInstant(UTC));

        enter("Alice");
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

    private void enter(String command) throws IOException {
        read(PROMPT);
        write(command);
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

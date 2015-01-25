package pduda.twitter.journey;

import org.junit.Before;
import org.junit.Test;
import pduda.twitter.domain.Message;
import pduda.twitter.domain.SocialNetworker;
import pduda.twitter.infrastructure.InMemoryMessages;
import pduda.twitter.ui.ConsoleOutput;
import pduda.twitter.ui.TwitterApplication;
import pduda.twitter.util.FixedClock;

import java.io.*;

import static java.lang.System.lineSeparator;
import static java.time.ZoneOffset.UTC;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static pduda.twitter.util.ObjectMother.someDay;

public class ReadingTimelineUiTest {
    public static final String PROMPT = "> ";
    public static final SocialNetworker alice = new SocialNetworker("Alice");
    public static final SocialNetworker bob = new SocialNetworker("Bob");
    private PrintWriter inWriter;
    private BufferedReader outReader;
    private InMemoryMessages messages;
    private FixedClock clock;

    @Before
    public void startApplication() throws Exception {
        PipedOutputStream inStream = new PipedOutputStream();
        inWriter = new PrintWriter(inStream, true);
        PipedInputStream outStream = new PipedInputStream();
        outReader = new BufferedReader(new InputStreamReader(outStream));

        BufferedReader in = new BufferedReader(new InputStreamReader(new PipedInputStream(inStream)));
        PrintWriter out = new PrintWriter(new PipedOutputStream(outStream), true);

        messages = new InMemoryMessages();
        clock = new FixedClock();
        new Thread(new TwitterApplication(in, messages, clock, new ConsoleOutput(out))).start();
    }

    @Test(timeout = 1000)
    public void journey() throws IOException {
        clock.fixAt(someDay().atTime(10, 0).toInstant(UTC));

        messages.addMessage(new Message(alice, "I love the weather today", someDay().atTime(9, 55).toInstant(UTC)));
        messages.addMessage(new Message(bob, "Damn! We lost!", someDay().atTime(9, 58).toInstant(UTC)));
        messages.addMessage(new Message(bob, "Good game though.", someDay().atTime(9, 59).toInstant(UTC)));

        enter("Alice");
        assertOutputLines(
                "I love the weather today (5 minutes ago)"
        );

        enter("Bob");
        assertOutputLines(
                "Damn! We lost! (2 minutes ago)",
                "Good game though. (1 minute ago)"
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

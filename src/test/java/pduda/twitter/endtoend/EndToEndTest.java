package pduda.twitter.endtoend;

import org.junit.Before;
import pduda.twitter.main.TwitterApplication;
import pduda.twitter.persistence.InMemorySocialNetworkers;
import pduda.twitter.ui.ConsoleInput;
import pduda.twitter.ui.ConsoleOutput;
import pduda.twitter.util.FixedClock;

import java.io.*;
import java.time.LocalDateTime;

import static java.lang.System.lineSeparator;
import static java.time.ZoneOffset.UTC;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public abstract class EndToEndTest {
    protected FixedClock clock;
    private PrintWriter inWriter;
    private BufferedReader outReader;

    @Before
    public void startApplication() throws Exception {
        PipedOutputStream inStream = new PipedOutputStream();
        inWriter = new PrintWriter(inStream, true);
        PipedInputStream outStream = new PipedInputStream();
        outReader = new BufferedReader(new InputStreamReader(outStream));

        BufferedReader in = new BufferedReader(new InputStreamReader(new PipedInputStream(inStream)));
        PrintWriter out = new PrintWriter(new PipedOutputStream(outStream), true);

        clock = new FixedClock();
        new Thread(
                new TwitterApplication(
                        new InMemorySocialNetworkers(),
                        clock,
                        new ConsoleOutput(out),
                        new ConsoleInput(in)
                )
        ).start();
    }

    protected Runnable whenTimeIs(LocalDateTime localDateTime) {
        return () -> clock.fixAt(localDateTime.toInstant(UTC));
    }

    protected void enter(String command, Runnable context) throws IOException {
        read(ConsoleOutput.PROMPT);
        context.run();
        write(command);
    }

    protected void enter(String command) throws IOException {
        enter(command, () -> {
        });
    }

    protected void assertOutputLines(String... expectedOutput) throws IOException {
        for (String line : expectedOutput) {
            read(line + lineSeparator());
        }
    }

    private void read(String expectedOutput) throws IOException {
        int length = expectedOutput.length();
        char[] buffer = new char[length];
        outReader.read(buffer, 0, length);
        assertThat(String.valueOf(buffer), is(expectedOutput));
    }

    private void write(String input) {
        inWriter.println(input);
    }
}

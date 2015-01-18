package pduda.twitter.ui;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static java.lang.System.lineSeparator;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ReadingTimelineUiTest {
    public static final String PROMPT = "> ";
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

        new Thread(new TwitterApplication(in, out)).start();
    }

    @Test(timeout = 1000)
    public void journey() throws IOException {
        execute("Alice");
        readLines(
                "I love the weather today (5 minutes ago)"
        );

        execute("Bob");
        readLines(
                "Damn! We lost!",
                "Good game though."
        );

        execute("quit");
    }

    private void execute(String command) throws IOException {
        read(PROMPT);
        write(command);
    }

    private void read(String expectedOutput) throws IOException {
        int length = expectedOutput.length();
        char[] buffer = new char[length];
        outReader.read(buffer, 0, length);
        assertThat(String.valueOf(buffer), is(expectedOutput));
    }

    private void readLines(String... expectedOutput) throws IOException {
        for (String line : expectedOutput) {
            read(line + lineSeparator());
        }
    }

    private void write(String input) {
        inWriter.println(input);
    }

}

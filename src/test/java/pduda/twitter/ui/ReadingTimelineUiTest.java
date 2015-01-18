package pduda.twitter.ui;

import org.junit.Before;
import org.junit.Test;
import pduda.twitter.domain.Message;
import pduda.twitter.domain.SocialNetworker;
import pduda.twitter.infrastructure.InMemoryMessages;

import java.io.*;
import java.util.Date;

import static java.lang.System.lineSeparator;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ReadingTimelineUiTest {
    public static final String PROMPT = "> ";
    private PrintWriter inWriter;
    private BufferedReader outReader;
    private InMemoryMessages messages;

    @Before
    public void startApplication() throws Exception {
        PipedOutputStream inStream = new PipedOutputStream();
        inWriter = new PrintWriter(inStream, true);
        PipedInputStream outStream = new PipedInputStream();
        outReader = new BufferedReader(new InputStreamReader(outStream));

        BufferedReader in = new BufferedReader(new InputStreamReader(new PipedInputStream(inStream)));
        PrintWriter out = new PrintWriter(new PipedOutputStream(outStream), true);

        messages = new InMemoryMessages();
        new Thread(new TwitterApplication(in, out, messages)).start();
    }

    @Test(timeout = 1000)
    public void journey() throws IOException {
        messages.addMessage(new Message(new SocialNetworker("Alice"), "I love the weather today", new Date(1)));
        messages.addMessage(new Message(new SocialNetworker("Bob"), "Damn! We lost!", new Date(2)));
        messages.addMessage(new Message(new SocialNetworker("Bob"), "Good game though.", new Date(3)));

        enter("Alice");
        assertOutputLines(
                "I love the weather today (5 minutes ago)"
        );

        enter("Bob");
        assertOutputLines(
                "Damn! We lost! (5 minutes ago)",
                "Good game though. (5 minutes ago)"
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

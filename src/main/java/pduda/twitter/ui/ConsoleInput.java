package pduda.twitter.ui;

import java.io.BufferedReader;
import java.io.IOException;

public class ConsoleInput {
    private final BufferedReader in;

    public ConsoleInput(BufferedReader in) {
        this.in = in;
    }

    public String getCommand() {
        try {
            return in.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

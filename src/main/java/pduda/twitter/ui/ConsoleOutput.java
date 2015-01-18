package pduda.twitter.ui;

import java.io.PrintWriter;

public class ConsoleOutput {

    private final PrintWriter out;

    public ConsoleOutput(PrintWriter out) {
        this.out = out;
    }

    public void writeLineAndFlush(String line) {
        out.println(line);
        out.flush();
    }
}

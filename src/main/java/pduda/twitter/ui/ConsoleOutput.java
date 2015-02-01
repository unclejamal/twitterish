package pduda.twitter.ui;

import java.io.PrintWriter;

public class ConsoleOutput {

    public static final String PROMPT = "> ";
    private final PrintWriter out;

    public ConsoleOutput(PrintWriter out) {
        this.out = out;
    }

    public void writeLineAndFlush(String line) {
        out.println(line);
        out.flush();
    }

    public void showPrompt() {
        out.print(PROMPT);
        out.flush();
    }
}

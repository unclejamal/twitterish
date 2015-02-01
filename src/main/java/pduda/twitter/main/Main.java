package pduda.twitter.main;

import pduda.twitter.persistence.InMemorySocialNetworkers;
import pduda.twitter.ui.ConsoleInput;
import pduda.twitter.ui.ConsoleOutput;
import pduda.twitter.ui.RealClock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        new Main().start();
    }

    public void start() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        new Thread(
                new TwitterApplication(
                        new InMemorySocialNetworkers(),
                        new RealClock(),
                        new ConsoleOutput(out),
                        new ConsoleInput(in)
                )
        ).start();
    }
}

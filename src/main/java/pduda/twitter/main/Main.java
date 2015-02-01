package pduda.twitter.main;

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
        new Thread(
                new TwitterApplication(
                        new RealClock(),
                        new ConsoleOutput(new PrintWriter(System.out)),
                        new ConsoleInput(new BufferedReader(new InputStreamReader(System.in)))
                )
        ).start();
    }
}

package pduda.twitter.main;

import pduda.twitter.persistence.InMemoryMessages;
import pduda.twitter.ui.ConsoleOutput;
import pduda.twitter.ui.RealClock;
import pduda.twitter.ui.TwitterApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        new Thread(new TwitterApplication(in, new InMemoryMessages(), new RealClock(), new ConsoleOutput(out))).start();
    }
}

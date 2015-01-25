package pduda.twitter.ui;

public interface ConsoleRouter {
    void route(String command);

    boolean canRoute(String command);
}

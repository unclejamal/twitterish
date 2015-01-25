package pduda.twitter.ui;

interface ConsoleRouter {
    void route(String command);

    boolean canRoute(String command);
}

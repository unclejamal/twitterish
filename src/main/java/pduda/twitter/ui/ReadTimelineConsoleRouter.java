package pduda.twitter.ui;

import pduda.twitter.domain.SocialNetworker;

public class ReadTimelineConsoleRouter implements ConsoleRouter {

    private final ReadTimelineController controller;

    public ReadTimelineConsoleRouter(ReadTimelineController controller) {
        this.controller = controller;
    }

    @Override
    public void route(String command) {
        controller.execute(new SocialNetworker(command));
    }

    @Override
    public boolean canRoute(String command) {
        return true;
    }
}

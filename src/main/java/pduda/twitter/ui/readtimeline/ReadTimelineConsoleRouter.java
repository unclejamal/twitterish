package pduda.twitter.ui.readtimeline;

import pduda.twitter.domain.AccountName;
import pduda.twitter.ui.ConsoleRouter;

public class ReadTimelineConsoleRouter implements ConsoleRouter {

    private final ReadTimelineController controller;

    public ReadTimelineConsoleRouter(ReadTimelineController controller) {
        this.controller = controller;
    }

    @Override
    public void route(String command) {
        controller.execute(new AccountName(command));
    }

    @Override
    public boolean canRoute(String command) {
        return true;
    }
}

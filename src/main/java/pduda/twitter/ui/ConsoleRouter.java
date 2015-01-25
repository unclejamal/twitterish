package pduda.twitter.ui;

import pduda.twitter.domain.SocialNetworker;

public class ConsoleRouter {
    private final ReadTimelineController readTimelineController;
    private final PostMessageController postMessageController;

    public ConsoleRouter(ReadTimelineController readTimelineController, PostMessageController postMessageController) {
        this.readTimelineController = readTimelineController;
        this.postMessageController = postMessageController;
    }

    public void execute(String command) {
        if (command.contains("Pawel")) {
            postMessageController.execute(new SocialNetworker("Pawel"), "Hello!");
        } else {
          readTimelineController.commandEntered(command);
        }
    }
}

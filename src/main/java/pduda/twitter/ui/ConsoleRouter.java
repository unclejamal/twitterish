package pduda.twitter.ui;

import pduda.twitter.domain.SocialNetworker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleRouter {
    private final ReadTimelineController readTimelineController;
    private final PostMessageController postMessageController;

    public ConsoleRouter(ReadTimelineController readTimelineController, PostMessageController postMessageController) {
        this.readTimelineController = readTimelineController;
        this.postMessageController = postMessageController;
    }

    public void routeCommand(String command) {
        Pattern postMessagePattern = Pattern.compile("^(\\w+) -> (.+)$");

        if (command.matches(postMessagePattern.pattern())) {
            Matcher matcher = getMatcher(command, postMessagePattern);
            String socialNetworker = matcher.group(1);
            String message = matcher.group(2);
            postMessageController.execute(new SocialNetworker(socialNetworker), message);

        } else {
            readTimelineController.commandEntered(new SocialNetworker(command));
        }
    }

    public Matcher getMatcher(String command, Pattern postMessagePattern) {
        Matcher matcher = postMessagePattern.matcher(command);
        matcher.find();
        return matcher;
    }
}

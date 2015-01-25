package pduda.twitter.ui;

import pduda.twitter.domain.SocialNetworker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleRouter {
    private final ReadTimelineController readTimelineController;
    private final PostMessageController postMessageController;
    private final WallController wallController;

    public ConsoleRouter(ReadTimelineController readTimelineController,
                         PostMessageController postMessageController,
                         WallController wallController) {
        this.readTimelineController = readTimelineController;
        this.postMessageController = postMessageController;
        this.wallController = wallController;
    }

    public void routeCommand(String command) {
        // TODO this is getting long

        Pattern postMessagePattern = Pattern.compile("^(\\w+) -> (.+)$");
        Pattern wallPattern = Pattern.compile("^(\\w+) wall$");

        if (command.matches(postMessagePattern.pattern())) {
            Matcher matcher = getMatcher(command, postMessagePattern);
            String socialNetworker = matcher.group(1);
            String message = matcher.group(2);
            postMessageController.execute(new SocialNetworker(socialNetworker), message);

        } else if (command.matches(wallPattern.pattern())) {
            Matcher matcher = getMatcher(command, wallPattern);
            String socialNetworker = matcher.group(1);
            wallController.execute(new SocialNetworker(socialNetworker));

        } else {
            readTimelineController.execute(new SocialNetworker(command));
        }
    }

    public Matcher getMatcher(String command, Pattern postMessagePattern) {
        Matcher matcher = postMessagePattern.matcher(command);
        matcher.find();
        return matcher;
    }
}

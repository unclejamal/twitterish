package pduda.twitter.ui;

import pduda.twitter.domain.SocialNetworker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostMessageConsoleRouter implements ConsoleRouter {
    public static final Pattern PATTERN = Pattern.compile("^(\\w+) -> (.+)$");
    private final PostMessageController controller;

    public PostMessageConsoleRouter(PostMessageController controller) {
        this.controller = controller;
    }

    @Override
    public void route(String command) {
        Matcher matcher = getMatcher(command, PATTERN);
        String socialNetworker = matcher.group(1);
        String message = matcher.group(2);
        controller.execute(new SocialNetworker(socialNetworker), message);
    }

    public Matcher getMatcher(String command, Pattern postMessagePattern) {
        Matcher matcher = postMessagePattern.matcher(command);
        matcher.find();
        return matcher;
    }

    @Override
    public boolean canRoute(String command) {
        return command.matches(PATTERN.pattern());
    }
}

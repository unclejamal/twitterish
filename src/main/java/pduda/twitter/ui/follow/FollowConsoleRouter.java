package pduda.twitter.ui.follow;

import pduda.twitter.domain.AccountName;
import pduda.twitter.ui.ConsoleRouter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FollowConsoleRouter implements ConsoleRouter {
    public static final Pattern PATTERN = Pattern.compile("^(\\w+) follows (\\w+)$");
    private final FollowController controller;

    public FollowConsoleRouter(FollowController controller) {
        this.controller = controller;
    }

    @Override
    public void route(String command) {
        Matcher matcher = getMatcher(command, PATTERN);
        String follower = matcher.group(1);
        String followee = matcher.group(2);
        controller.execute(new AccountName(follower), new AccountName(followee));
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

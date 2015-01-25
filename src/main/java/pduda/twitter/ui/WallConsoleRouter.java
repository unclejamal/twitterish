package pduda.twitter.ui;

import pduda.twitter.domain.SocialNetworker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WallConsoleRouter implements ConsoleRouter {

    public static final Pattern PATTERN = Pattern.compile("^(\\w+) wall$");
    private final WallController controller;

    public WallConsoleRouter(WallController controller) {
        this.controller = controller;
    }

    @Override
    public void route(String command) {
        Pattern wallPattern = PATTERN;
        Matcher matcher = getMatcher(command, wallPattern);
        String socialNetworker = matcher.group(1);
        controller.execute(new SocialNetworker(socialNetworker));
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
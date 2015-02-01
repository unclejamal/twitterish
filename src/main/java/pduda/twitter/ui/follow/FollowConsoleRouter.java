package pduda.twitter.ui.follow;

import pduda.twitter.domain.AccountName;
import pduda.twitter.ui.RegularExpressionConsoleRouter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FollowConsoleRouter extends RegularExpressionConsoleRouter {
    private final FollowController controller;

    public FollowConsoleRouter(FollowController controller) {
        this.controller = controller;
    }

    public void route(Matcher matcher) {
        String follower = matcher.group(1);
        String followee = matcher.group(2);
        controller.execute(new AccountName(follower), new AccountName(followee));
    }

    @Override
    protected Pattern getRegularExpression() {
        return Pattern.compile("^(\\w+) follows (\\w+)$");
    }
}
package pduda.twitter.ui.postmessage;

import pduda.twitter.domain.AccountName;
import pduda.twitter.ui.RegularExpressionConsoleRouter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostMessageConsoleRouter extends RegularExpressionConsoleRouter {
    private final PostMessageController controller;

    public PostMessageConsoleRouter(PostMessageController controller) {
        this.controller = controller;
    }

    @Override
    protected void route(Matcher matcher) {
        String accountName = matcher.group(1);
        String message = matcher.group(2);
        controller.execute(new AccountName(accountName), message);
    }

    @Override
    protected Pattern getRegularExpression() {
        return Pattern.compile("^(\\w+) -> (.+)$");
    }
}

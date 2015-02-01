package pduda.twitter.ui.wall;

import pduda.twitter.domain.AccountName;
import pduda.twitter.ui.RegularExpressionConsoleRouter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WallConsoleRouter extends RegularExpressionConsoleRouter {
    private final WallController controller;

    public WallConsoleRouter(WallController controller) {
        this.controller = controller;
    }

    @Override
    protected void route(Matcher matcher) {
        String accountName = matcher.group(1);
        controller.execute(new AccountName(accountName));
    }

    @Override
    protected Pattern getRegularExpression() {
        return Pattern.compile("^(\\w+) wall$");
    }
}
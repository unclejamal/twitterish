package pduda.twitter.ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class RegularExpressionConsoleRouter implements ConsoleRouter {
    @Override
    public boolean canRoute(String command) {
        return command.matches(getRegularExpression().pattern());
    }

    @Override
    public void route(String command) {
        route(getMatcher(command, getRegularExpression()));
    }

    private Matcher getMatcher(String command, Pattern pattern) {
        Matcher matcher = pattern.matcher(command);
        matcher.find();
        return matcher;
    }

    protected abstract void route(Matcher matcher);

    protected abstract Pattern getRegularExpression();
}

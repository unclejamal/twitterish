package pduda.twitter.ui.postmessage;

import pduda.twitter.domain.AccountName;
import pduda.twitter.domain.usecase.PostMessage;

public class PostMessageController {
    private final PostMessage postMessage;

    public PostMessageController(PostMessage postMessage) {
        this.postMessage = postMessage;
    }

    public void execute(AccountName accountName, String message) {
        postMessage.execute(accountName, message);
    }
}

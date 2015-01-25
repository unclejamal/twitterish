package pduda.twitter.ui.postmessage;

import pduda.twitter.domain.SocialNetworker;
import pduda.twitter.domain.usecase.PostMessage;

public class PostMessageController {
    private final PostMessage postMessage;

    public PostMessageController(PostMessage postMessage) {
        this.postMessage = postMessage;
    }

    public void execute(SocialNetworker socialNetworker, String message) {
        postMessage.execute(socialNetworker, message);
    }
}

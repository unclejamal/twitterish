package pduda.twitter.ui;

import pduda.twitter.domain.SocialNetworker;

public class PostMessageController {
    public void execute(SocialNetworker socialNetworker, String message) {
        System.out.println("socialNetworker = " + socialNetworker);
    }
}

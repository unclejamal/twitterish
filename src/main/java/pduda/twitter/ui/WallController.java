package pduda.twitter.ui;

import pduda.twitter.domain.SocialNetworker;
import pduda.twitter.domain.Timeline;
import pduda.twitter.domain.usecase.Wall;

public class WallController {

    private final Wall wall;
    private final WallView wallView;

    public WallController(Wall wall, WallView wallView) {
        this.wall = wall;
        this.wallView = wallView;
    }

    public void execute(SocialNetworker socialNetworker) {
        Timeline timeline = wall.execute(socialNetworker);
        wallView.present(timeline);
    }
}

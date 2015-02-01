package pduda.twitter.ui.wall;

import pduda.twitter.domain.AccountName;
import pduda.twitter.domain.Timeline;
import pduda.twitter.domain.usecase.Wall;

public class WallController {

    private final Wall wall;
    private final WallView wallView;

    public WallController(Wall wall, WallView wallView) {
        this.wall = wall;
        this.wallView = wallView;
    }

    public void execute(AccountName accountName) {
        Timeline timeline = wall.execute(accountName);
        wallView.present(timeline);
    }
}

/**************
 * Shira Fisher
 * Computer Science Student
 **************/

import geometry_primitives.Counter;

/** .
 * The main class of BallRemover
 * In charge of removing blocks from the game, as well as keeping count of the number of blocks that remain
 */
public class BallRemover implements HitListener {
    private final Game game;
    private final Counter remainingBalls;
    /**
     * @param game
     * @param remainingBalls
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    };
    /**
     * @param beingHit call this method is the beingHit object is hit
     * @param hitter is the Ball that's doing the hitting
     * Blocks that are hit should be removed from the game
     * Remember to remove this listener from the block that is being removed from the game
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        remainingBalls.decrease(1);
    };
}
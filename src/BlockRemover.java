/**************
 * Shira Fisher
 * Computer Science Student
 **************/

import geometry_primitives.Counter;

/** .
 * The main class of BlockRemover
 * In charge of removing blocks from the game, as well as keeping count of the number of blocks that remain
 */
public class BlockRemover implements HitListener {
    private final Game game;
    private final Counter remainingBlocks;
    /**
     * @param game
     * @param remainingBlocks
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    };
    /**
     * @param beingHit call this method is the beingHit object is hit
     * @param hitter is the Ball that's doing the hitting
     * Blocks that are hit should be removed from the game
     * Remember to remove this listener from the block that is being removed from the game
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(game);
        remainingBlocks.decrease(1);
    };
}

/**************
 * Shira Fisher
 * Computer Science Student
 **************/

import geometry_primitives.Counter;

/** .
 * The main class of ScoreTrackingListener
 */
public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;
    /**
     * @param scoreCounter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    };
    /**
     * @return counter value
     */
    public int getValue() {
        return this.currentScore.getValue();
    };
    /**
     * @param beingHit call this method is the beingHit object is hit
     * @param hitter is the Ball that's doing the hitting
     * Add points to the score
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    };
}
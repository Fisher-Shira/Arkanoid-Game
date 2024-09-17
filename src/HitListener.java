/**************
 * Shira Fisher
 * Computer Science Student
 **************/

/** .
 * The main interface of HitListener
 */
public interface HitListener {
    /**
     * @param beingHit call this method is the beingHit object is hit
     * @param hitter is the Ball that's doing the hitting
     */
    void hitEvent(Block beingHit, Ball hitter);
}
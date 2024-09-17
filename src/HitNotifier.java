/**************
 * Shira Fisher
 * Computer Science Student
 **************/

/** .
 * The main interface of HitNotifier
 */
public interface HitNotifier {
    /**
     * @param hl to add as a listener to hit events.
     */
    void addHitListener(HitListener hl);
    /**
     * @param hl to remove from the list of listeners to hit events
     */
    void removeHitListener(HitListener hl);
        /**
     * @param hitter ball that hit the Block
     * Notify all listeners about a hit event
     */
    void notifyHit(Ball hitter);
}
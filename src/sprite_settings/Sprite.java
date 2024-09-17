/**************
 * Shira Fisher
 * Computer Science Student
 **************/

package sprite_settings;

import java.awt.Graphics;

/** .
 * The main interface of Sprite
 */
public interface Sprite {
    /**
     * @param g Graphics to draw the sprite to the screen
     */
    void drawOn(Graphics g);
    /** .
     * notify the sprite that time has passed
     */
    void timePassed();
}
